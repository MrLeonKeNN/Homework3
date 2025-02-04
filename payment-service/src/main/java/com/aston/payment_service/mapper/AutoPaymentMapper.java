package com.aston.payment_service.mapper;

import com.aston.payment_service.dto.request.AutoPaymentDtoRequest;
import com.aston.payment_service.entity.AutoPayments;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = GlobalMapperConfiguration.class)
public interface AutoPaymentMapper {

    AutoPayments toEntity(AutoPaymentDtoRequest autoPaymentDtoRequest);
}
