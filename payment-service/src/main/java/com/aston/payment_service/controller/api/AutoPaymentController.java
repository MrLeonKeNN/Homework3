package com.aston.payment_service.controller.api;

import com.aston.payment_service.dto.request.AutoPaymentDtoRequest;
import com.aston.payment_service.dto.response.SuccessDtoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/v1/payment-service")
public interface AutoPaymentController {

    /**
     * Creates a new auto-payment based on the provided request data.
     *
     * @param autoPaymentDtoRequest the request body containing auto-payment details, must be valid
     * @return ResponseEntity containing a success response with details of the created auto-payment
     */
    @PostMapping("/auto-payments")
    ResponseEntity<SuccessDtoResponse> createAutoPayment(@RequestBody @Valid  AutoPaymentDtoRequest autoPaymentDtoRequest);

    /**
     * Test endpoint.
     * @return
     */
    @GetMapping("/test")
    ResponseEntity<Void> test();
}
