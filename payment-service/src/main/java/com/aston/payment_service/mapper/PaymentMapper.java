package com.aston.payment_service.mapper;

import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = GlobalMapperConfiguration.class)
public interface PaymentMapper {

    Payment fromAutoPayment(AutoPayments autoPayments);
}
