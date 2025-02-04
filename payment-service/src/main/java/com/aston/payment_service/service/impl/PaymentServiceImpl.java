package com.aston.payment_service.service.impl;

import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.entity.Payment;
import com.aston.payment_service.repository.PaymentRepository;
import com.aston.payment_service.mapper.PaymentMapper;
import com.aston.payment_service.service.api.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;

    private final PaymentRepository paymentRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Payment createPayment(AutoPayments autoPayments) {
        return paymentRepository.save(paymentMapper.fromAutoPayment(autoPayments));
    }
}
