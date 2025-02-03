package com.aston.payment_service.entity.enums;

public enum PaymentStatus {

    INPROGRESS,

    /**
     * Платеж выполнен успешно.
     */
    DONE,

    /**
     * Платеж был отменён.
     */
    CANCELED,

    /**
     * Платеж не был выполнен из-за ошибки или отказа.
     */
    FAIL
}
