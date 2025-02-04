package com.example.account_service.controller.api;

import com.example.account_service.dto.request.CreateAccountDtoRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;


@RequestMapping("/v1/account-service")
public interface AccountController {

    @GetMapping("/details/{accountID}")
    ResponseEntity<Void> getAccountDetails(@PathVariable UUID accountID);

    @PostMapping("/create")
    ResponseEntity<Void> createAccount(@RequestBody @Valid CreateAccountDtoRequest account);
}
