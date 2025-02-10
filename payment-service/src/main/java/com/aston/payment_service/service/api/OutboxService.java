package com.aston.payment_service.service.api;

import com.aston.payment_service.entity.Entities;
import com.aston.payment_service.entity.Payment;
import com.aston.payment_service.entity.ServiceEntity;

public interface OutboxService {

    void save(Payment payment, Entities entities, ServiceEntity serviceEntity);

    void processOutbox();

}
