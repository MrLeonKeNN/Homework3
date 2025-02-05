package com.aston.payment_service.mapper;

import com.aston.payment_service.entity.AutoPayments;
import com.example.JsonPojo.kafka.pojo.CreatePaymentOBS;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = GlobalMapperConfiguration.class)
public interface CreatePaymentOBSMapper {

    CreatePaymentOBS toPojo(AutoPayments autoPayments);
}
