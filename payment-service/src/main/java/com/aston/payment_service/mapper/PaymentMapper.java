package com.aston.payment_service.mapper;

import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = GlobalMapperConfiguration.class)
public interface PaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "autoPayments", expression = "java(autoPayments)")
    Payment fromAutoPayment(AutoPayments autoPayments);
}
