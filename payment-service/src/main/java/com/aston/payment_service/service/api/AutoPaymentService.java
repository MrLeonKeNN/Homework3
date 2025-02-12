package com.aston.payment_service.service.api;

import com.aston.payment_service.dto.request.AutoPaymentDtoRequest;
import com.aston.payment_service.dto.response.SuccessDtoResponse;

public interface AutoPaymentService {

    /**
     * Creates a new auto-payment based on the provided request data.
     *
     * @param request the request body containing auto-payment details, must be valid
     * @return ResponseEntity containing a success response with details of the created auto-payment
     */
    SuccessDtoResponse createAutoPayment(AutoPaymentDtoRequest request);
}
