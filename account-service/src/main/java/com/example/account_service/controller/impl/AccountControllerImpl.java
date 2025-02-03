package com.example.account_service.controller.impl;

import com.example.account_service.controller.api.AccountController;
import com.example.account_service.dto.request.CreateAccountDtoRequest;
import com.example.account_service.service.api.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;

    @Override
    public ResponseEntity<Void> getAccountDetails(UUID accountID) {
        return null;
    }

    @Override
    public ResponseEntity<Void> createAccount(CreateAccountDtoRequest account) {
        accountService.createAccount(account);
        return ResponseEntity.ok().build();
    }
}
