package com.example.account_service.controller.impl;

import com.example.account_service.controller.api.AccountController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class AccountControllerImpl implements AccountController {

    @Override
    public ResponseEntity<Void> getAccountDetails(UUID accountID) {
        return null;
    }
}
