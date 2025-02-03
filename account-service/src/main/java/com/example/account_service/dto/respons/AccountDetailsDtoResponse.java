package com.example.account_service.dto.respons;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AccountDetailsDtoResponse(

        String accountNumber,

        String typeAccount,

        String holdBalance,

        String statusName,

        Instant createdAt,

        Instant closedAt,

        String currencyName,

        String masterAccount

        ) {
}
