package com.aston.payment_service.service.api;

import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.entity.Payment;

public interface PaymentService {

    /**
     * Create Payment from autoPayment
     * @param autoPayments
     * @return
     */
    Payment createPayment(AutoPayments autoPayments);
}
