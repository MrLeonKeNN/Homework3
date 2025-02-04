package com.aston.payment_service.dto.request;

import com.aston.payment_service.entity.enums.Currency;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link com.aston.payment_service.mapper.AutoPaymentDto}
 */
@Builder
public record AutoPaymentDtoRequest(

        /**
         * A unique identifier for the auto payment.
         */
        UUID clientId,

        /**
         * The currency in which the payment is made.
         */
        Currency currency,

        /**
         * The date and time when the auto payment starts.
         */
        Instant startDate,

        /**
         * The date and time when the auto payment ends.
         */
        Instant endDate,

        /**
         * The frequency of the payment execution.
         */
        String periodicity,

        /**
         * The account number of the sender.
         */
        String senderAccountNumber,

        /**
         * The amount of money to be paid.
         */
        BigDecimal amount,

        /**
         * A comment or note about the payment.
         */
        String comment,

        /**
         * The value of the payment detail.
         */
        String value,

        /**
         * A unique identifier for the service.
         */
        UUID serviceId,

        /**
         * The status of the auto payment.
         */
        Boolean isActive,

        /**
         * A description of the payment detail.
         */
        String description,

        /**
         * The name of the payment detail.
         */
        String name,

        String measuredTimeZone
) {
}
