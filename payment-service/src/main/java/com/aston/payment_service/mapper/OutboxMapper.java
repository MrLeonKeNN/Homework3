package com.aston.payment_service.mapper;

import com.aston.payment_service.entity.Outbox;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", config = GlobalMapperConfiguration.class)
public interface OutboxMapper {

    @Mapping(target = "aggregate" , expression = "java(Aggregate.PAYMENT)")
    @Mapping(target = "status" , expression = "java(OutboxStatus.WAITING)")
    @Mapping(target = "aggregateId", source = "uuid")
    Outbox createOutbox(UUID uuid, String payload);
}
