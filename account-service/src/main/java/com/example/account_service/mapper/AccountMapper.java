package com.example.account_service.mapper;

import com.example.account_service.dto.request.CreateAccountDtoRequest;
import com.example.account_service.dto.respons.AccountDetailsDtoResponse;
import com.example.account_service.dto.respons.AccountDtoResponse;
import com.example.account_service.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

//    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);


    AccountDtoResponse toDto(Account account);

    Account toEntity(CreateAccountDtoRequest request);
}
