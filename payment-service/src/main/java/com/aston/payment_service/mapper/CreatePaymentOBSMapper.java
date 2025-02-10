package com.aston.payment_service.mapper;

import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.entity.Entities;
import com.aston.payment_service.entity.Payment;
import com.aston.payment_service.entity.ServiceEntity;
import com.example.JsonPojo.kafka.pojo.CreatePaymentOBS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = GlobalMapperConfiguration.class)
public interface CreatePaymentOBSMapper {

    @Mapping(target = "clientId", source = "entities.clientId")
    @Mapping(target = "entitiesKPP", source = "entities.kpp")
    @Mapping(target = "entitiesOGRN", source = "entities.ogrn")
    @Mapping(target = "receiverAccountNumber", source = "entities.id")
    @Mapping(target = "transactionDate", expression = "java(java.time.Instant.now())")
    CreatePaymentOBS toPojo(Payment payment, Entities entities, ServiceEntity service);
}
