package com.example.account_service.service.api;

import com.example.account_service.dto.request.CreateAccountDtoRequest;

import java.util.UUID;

public interface AccountService {

    void getAccountDetails(UUID id);

    void createAccount(CreateAccountDtoRequest account);
}
