package com.aston.payment_service.mapper;

import com.aston.payment_service.entity.Outbox;
import com.example.JsonPojo.kafka.pojo.CreatePaymentOBS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", config = GlobalMapperConfiguration.class)
public interface OutboxMapper {

    @Mapping(target = "aggregate" , expression = "java(com.aston.payment_service.entity.enums.Aggregate.PAYMENT)")
    @Mapping(target = "status" , expression = "java(com.aston.payment_service.entity.enums.OutboxStatus.WAITING)")
    @Mapping(target = "aggregateId", source = "uuid")
    Outbox createOutbox(UUID uuid, CreatePaymentOBS payload);
}
