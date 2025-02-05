package com.aston.payment_service.mapper;

import com.aston.payment_service.dto.request.AutoPaymentDtoRequest;
import com.aston.payment_service.entity.AutoPayments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = GlobalMapperConfiguration.class)
public interface AutoPaymentMapper {

    @Mapping(target = "lastPaymentDate" , expression = "java(java.time.Instant.now())")
    AutoPayments toEntity(AutoPaymentDtoRequest autoPaymentDtoRequest);
}
