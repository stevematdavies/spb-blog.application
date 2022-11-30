package com.steve.springbootblogapplication.service;

import com.steve.springbootblogapplication.model.Account;
import com.steve.springbootblogapplication.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService {

    private final IAccountRepository repository;

    @Autowired
    public AccountService(IAccountRepository repository) {
        this.repository = repository;
    }

    public Optional<Account> getAccount(Long id){
        return repository.findById(id);
    }

    public Optional<Account> getAccountByEmail(String email){
        return repository.findAccountByEmail(email);
    }

    public Account save(Account account){
        if (account.getId() == null){
            account.setCreatedAt(LocalDateTime.now());
        }
        return repository.save(account);
    }
}
