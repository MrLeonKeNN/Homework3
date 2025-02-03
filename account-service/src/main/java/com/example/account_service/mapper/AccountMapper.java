package com.example.account_service.mapper;

import com.example.account_service.dto.respons.AccountDetailsDtoResponse;
import com.example.account_service.entity.Account;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {

    AccountDetailsDtoResponse toDto(Account account);
}
