package com.aston.payment_service.controller.impl;

import com.aston.payment_service.controller.api.AutoPaymentController;
import com.aston.payment_service.dto.request.AutoPaymentDtoRequest;
import com.aston.payment_service.dto.response.SuccesDtoResponse;
import com.aston.payment_service.service.api.AutoPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AutoPaymentControllerImpl implements AutoPaymentController {

    private final AutoPaymentService autoPaymentService;

    @Override
    public ResponseEntity<SuccesDtoResponse> createAutoPayment(AutoPaymentDtoRequest autoPaymentDtoRequest) {
        System.out.println(autoPaymentDtoRequest);
        return ResponseEntity.ok(autoPaymentService.createAutoPayment(autoPaymentDtoRequest));
    }
}
