package com.example.account_service.service.impl;

import com.example.account_service.entity.Account;
import com.example.account_service.repository.AccountRepository;
import com.example.account_service.service.api.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public void getAccountDetails(UUID id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
