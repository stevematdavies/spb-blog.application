package com.steve.springbootblogapplication.repository;

import com.steve.springbootblogapplication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    public Optional<Account> findAccountByEmail(String email);
}
