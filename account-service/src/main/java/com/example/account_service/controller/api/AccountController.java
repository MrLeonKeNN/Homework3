package com.example.account_service.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;


@RequestMapping("/v1")
public interface AccountController {

    @GetMapping("/details/{accountID}")
    ResponseEntity<Void> getAccountDetails(@PathVariable UUID accountID);
}
