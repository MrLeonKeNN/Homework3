package com.aston.payment_service.dto.response;

import lombok.Builder;

/**
 * Success dto response.
 * @param successMessage
 */
@Builder
public record SuccessDtoResponse(
        String successMessage
) {
}
