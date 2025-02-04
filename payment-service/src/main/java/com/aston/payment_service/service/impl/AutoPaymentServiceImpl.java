package com.aston.payment_service.service.impl;

import com.aston.payment_service.dto.request.AutoPaymentDtoRequest;
import com.aston.payment_service.dto.response.SuccesDtoResponse;
import com.aston.payment_service.entity.AutoPaymentField;
import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.mapper.AutoPaymentMapper;
import com.aston.payment_service.mapper.FieldMapper;
import com.aston.payment_service.repository.AutoPaymentFieldRepository;
import com.aston.payment_service.repository.AutoPaymentsRepository;
import com.aston.payment_service.repository.FieldRepository;
import com.aston.payment_service.service.api.AutoPaymentService;
import com.aston.payment_service.service.api.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class AutoPaymentServiceImpl implements AutoPaymentService {

    private final AutoPaymentsRepository autoPaymentsRepository;
    private final FieldRepository fieldRepository;

    private final AutoPaymentMapper autoPaymentMapper;
    private final FieldMapper fieldMapper;

    private final AutoPaymentFieldRepository autoPaymentFieldRepository;
    private final PaymentService paymentService;

    @Override
    @Transactional()
    public SuccesDtoResponse createAutoPayment(AutoPaymentDtoRequest request) {
        AutoPayments saveAutoPayment = autoPaymentMapper.toEntity(request);
        paymentService.createPayment(saveAutoPayment);

        validTimezone(request.measuredTimeZone());

        AutoPayments autoPayments = autoPaymentsRepository.save(saveAutoPayment);
        AutoPaymentField autoPaymentField = AutoPaymentField.builder()
                .autoPayments(autoPayments)
                .value(request.value())
                .field(fieldRepository.findByName(request.name())
                        .orElseGet(() -> fieldRepository.save(fieldMapper.fromDto(request))))
                .build();
        autoPaymentFieldRepository.save(autoPaymentField);
        log.info("Auto payment created successfully for user {}", autoPayments.getClientId());
        return SuccesDtoResponse.builder()
                .successMessage("Auto payment created successfully")
                .build();
    }

    private void validTimezone(String timezone) {
        try {
            ZoneId.of(timezone);
        } catch (DateTimeException e) {
            log.error("Invalid timezone {}", timezone);
            new RuntimeException(e.getMessage());
        }
    }
}
