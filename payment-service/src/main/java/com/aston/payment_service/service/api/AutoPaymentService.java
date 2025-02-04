package com.aston.payment_service.service.api;

import com.aston.payment_service.dto.request.AutoPaymentDtoRequest;
import com.aston.payment_service.dto.response.SuccesDtoResponse;

public interface AutoPaymentService {

    SuccesDtoResponse createAutoPayment(AutoPaymentDtoRequest request);
}
