package com.example.account_service.dto.request;

import com.example.interfaces.StarterValidation;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateAccountDtoRequest(
        UUID clientId,
        UUID departmentId,
        @StarterValidation(accountNumber = true)
        String accountNumber,
        String typeAccount,
        BigDecimal accountBalance,
        BigDecimal holdBalance,
        String statusName,
        String blockReason,
        Boolean masterAccount,
        String currencyName,
        String nameAccount
) {
}
