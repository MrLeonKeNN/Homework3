package com.aston.payment_service.dto.response;

import java.math.BigDecimal;
import java.time.Instant;

public record AccountDtoResponse(

        String accountNumber,

        String typeAccount,

        BigDecimal accountBalance,

        BigDecimal holdBalance,

        String statusName,

        Instant createdAt,

        Instant closedAt,

        String currencyName,

        boolean masterAccount

) {
}
