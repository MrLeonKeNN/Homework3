package com.aston.payment_service.service.api;

import com.aston.payment_service.entity.Entities;
import com.aston.payment_service.entity.Payment;
import com.aston.payment_service.entity.ServiceEntity;

public interface OutboxService {

    /**
     * Save outbox entity to repository.
     * @param payment
     * @param entities
     * @param serviceEntity
     */
    void save(Payment payment, Entities entities, ServiceEntity serviceEntity);

    /**
     *
     */
    void processOutbox();

}
