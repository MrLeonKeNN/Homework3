package com.aston.payment_service.controller.api;

import com.aston.payment_service.dto.request.AutoPaymentDtoRequest;
import com.aston.payment_service.dto.response.SuccesDtoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/v1/payment-service")
public interface AutoPaymentController {

    @PostMapping("/auto-payments")
    ResponseEntity<SuccesDtoResponse> createAutoPayment(@RequestBody @Valid  AutoPaymentDtoRequest autoPaymentDtoRequest);
}
