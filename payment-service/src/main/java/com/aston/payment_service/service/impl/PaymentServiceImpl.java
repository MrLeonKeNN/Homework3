package com.aston.payment_service.service.impl;

import com.aston.payment_service.dto.response.AccountDtoResponse;
import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.entity.Entities;
import com.aston.payment_service.entity.Payment;
import com.aston.payment_service.entity.ServiceEntity;
import com.aston.payment_service.mapper.CreatePaymentOBSMapper;
import com.aston.payment_service.mapper.OutboxMapper;
import com.aston.payment_service.mapper.PaymentMapper;
import com.aston.payment_service.repository.EntitiesRepository;
import com.aston.payment_service.repository.EntitiesServicesRepository;
import com.aston.payment_service.repository.OutboxRepository;
import com.aston.payment_service.repository.PaymentRepository;
import com.aston.payment_service.repository.ServiceEntityRepository;
import com.aston.payment_service.service.api.PaymentService;
import com.example.JsonPojo.kafka.pojo.CreatePaymentOBS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.InvalidRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final OutboxMapper outboxMapper;
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;
    private final CreatePaymentOBSMapper createPaymentOBSMapper;
    private final ServiceEntityRepository serviceEntityRepository;

    private final EntitiesRepository entitiesRepository;

    private final EntitiesServicesRepository entitiesServicesRepository;

    private final OutboxRepository outboxRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Payment createPayment(AutoPayments autoPayments) {
        AccountDtoResponse accountDtoResponse = webClientBuilder.build()
                .get()
                .uri("http://account-service/v1/account-service/details/87eaa4fc-4cf5-43b3-8b9a-ed9bf6e19fb4")
                .retrieve()
                .bodyToMono(AccountDtoResponse.class)
                .onErrorResume(trow -> Mono.error(new RuntimeException(trow.getMessage())))
                .block();
        validClientAccount(accountDtoResponse, autoPayments.getAmount());

        Entities entities = entitiesRepository.findByClientId(autoPayments.getClientId())
                .orElseThrow(NullPointerException::new);
        Payment payment = paymentRepository.save(paymentMapper.fromAutoPayment(autoPayments));
        ServiceEntity serviceEntity = serviceEntityRepository.findById(autoPayments.getService()
                        .getId())
                .orElseThrow(RuntimeException::new);
        CreatePaymentOBS createPaymentOBS = createPaymentOBSMapper.toPojo(autoPayments,entities, serviceEntity);
        outboxRepository.save(outboxMapper.createOutbox(payment.getId(), createPaymentOBS));
        return payment;
    }

    private void validClientAccount(AccountDtoResponse accountDetails, BigDecimal amount) {

        if (!accountDetails.statusName()
                .equals("ACTIVE")) {
            throw new InvalidRequestException("Account cannot process payment");
        }

        if (amount.compareTo(accountDetails.accountBalance()) > 0) {
            throw new InvalidRequestException("Insufficient funds on the account");
        }
    }
}
