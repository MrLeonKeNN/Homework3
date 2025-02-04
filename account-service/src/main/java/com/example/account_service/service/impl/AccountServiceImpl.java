package com.example.account_service.service.impl;

import com.example.account_service.dto.request.CreateAccountDtoRequest;
import com.example.account_service.entity.Account;
import com.example.account_service.mapper.AccountMapper;
import com.example.account_service.repository.AccountRepository;
import com.example.account_service.service.api.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public void getAccountDetails(UUID id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void createAccount(CreateAccountDtoRequest account) {
        Account account1 = accountMapper.toEntity(account);
        accountRepository.save(account1);
    }
}
