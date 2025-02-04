package com.aston.payment_service.dto.response;

import lombok.Builder;

@Builder
public record SuccesDtoResponse(
        String successMessage
) {
}
