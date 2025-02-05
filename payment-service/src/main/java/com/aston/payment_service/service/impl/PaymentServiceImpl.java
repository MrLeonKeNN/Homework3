package com.aston.payment_service.service.impl;

import com.aston.payment_service.dto.response.AccountDtoResponse;
import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.entity.Outbox;
import com.aston.payment_service.entity.Payment;
import com.aston.payment_service.entity.enums.Aggregate;
import com.aston.payment_service.mapper.PaymentMapper;
import com.aston.payment_service.repository.PaymentRepository;
import com.aston.payment_service.service.api.PaymentService;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.InvalidRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private WebClient.Builder webClientBuilder;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Payment createPayment(AutoPayments autoPayments) {
        AccountDtoResponse accountDtoResponse = webClientBuilder.build()
                .get()
                .uri("http://account-service/v1/account-service/details/87eaa4fc-4cf5-43b3-8b9a-ed9bf6e19fb4")
                .retrieve()
                .bodyToMono(AccountDtoResponse.class)
                .block();
        validClientAccount(accountDtoResponse, autoPayments.getAmount());
        return paymentRepository.save(paymentMapper.fromAutoPayment(autoPayments));
    }

    private void createOutbox() {
//        Outbox outbox = Outbox.builder()
//                .aggregate(Aggregate.PAYMENT)
//                .s()
//                .build();
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
