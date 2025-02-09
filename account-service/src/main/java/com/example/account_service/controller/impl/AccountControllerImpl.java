package com.example.account_service.controller.impl;

import com.example.account_service.controller.api.AccountController;
import com.example.account_service.dto.request.CreateAccountDtoRequest;
import com.example.account_service.dto.respons.AccountDtoResponse;
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
    public ResponseEntity<AccountDtoResponse> getAccountDetails(UUID accountId) {
        return ResponseEntity.ok(accountService.getAccountDetails(accountId));
    }

    @Override
    public ResponseEntity<Void> createAccount(CreateAccountDtoRequest account) {
        accountService.createAccount(account);
        return ResponseEntity.ok()
                .build();
    }

    @Override
    public ResponseEntity<String> test() {
        System.out.println("THIS INSTANCE !!!!!!!!");
        return ResponseEntity.ok("TEST");
    }
}
