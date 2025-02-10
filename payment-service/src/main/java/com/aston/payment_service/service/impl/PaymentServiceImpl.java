package com.aston.payment_service.service.impl;

import com.aston.payment_service.dto.response.AccountDtoResponse;
import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.entity.Entities;
import com.aston.payment_service.entity.Payment;
import com.aston.payment_service.entity.ServiceEntity;
import com.aston.payment_service.mapper.PaymentMapper;
import com.aston.payment_service.repository.EntitiesRepository;
import com.aston.payment_service.repository.PaymentRepository;
import com.aston.payment_service.repository.ServiceEntityRepository;
import com.aston.payment_service.service.api.OutboxService;
import com.aston.payment_service.service.api.PaymentService;
import com.aston.payment_service.webclient.WebClientRequests;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.InvalidRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final ServiceEntityRepository serviceEntityRepository;
    private final EntitiesRepository entitiesRepository;
    private final OutboxService outboxService;
    private final WebClientRequests webClientRequests;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Payment createPayment(AutoPayments autoPayments) {
        AccountDtoResponse accountDtoResponse = webClientRequests.getAccountDetails(autoPayments.getClientId());
        validClientAccount(accountDtoResponse, autoPayments.getAmount());

        Entities entities = entitiesRepository.findByClientId(autoPayments.getClientId())
                .orElseThrow(NullPointerException::new);
        Payment payment = paymentRepository.save(paymentMapper.fromAutoPayment(autoPayments));
        ServiceEntity serviceEntity = serviceEntityRepository.findById(autoPayments.getService()
                        .getId())
                .orElseThrow(RuntimeException::new);
        outboxService.save(payment, entities, serviceEntity);
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
