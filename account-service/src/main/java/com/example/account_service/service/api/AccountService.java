package com.example.account_service.service.api;

import com.example.account_service.dto.request.CreateAccountDtoRequest;
import com.example.account_service.dto.respons.AccountDtoResponse;
import com.example.account_service.entity.Account;

import java.util.UUID;

public interface AccountService {

    AccountDtoResponse getAccountDetails(UUID id);

    void createAccount(CreateAccountDtoRequest account);
}
