package com.aston.payment_service.dto.response;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Dto from account-service.
 * @param accountNumber
 * @param typeAccount
 * @param accountBalance
 * @param holdBalance
 * @param statusName
 * @param createdAt
 * @param closedAt
 * @param currencyName
 * @param masterAccount
 */
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
