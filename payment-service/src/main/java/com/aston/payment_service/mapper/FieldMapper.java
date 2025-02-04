package com.aston.payment_service.mapper;

import com.aston.payment_service.dto.request.AutoPaymentDtoRequest;
import com.aston.payment_service.entity.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = GlobalMapperConfiguration.class)
public interface FieldMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Field fromDto(AutoPaymentDtoRequest request);
}
