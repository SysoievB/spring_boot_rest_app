package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.entity.Account;
import com.app.spring_boot_rest_app.repository.AccountRepository;
import com.app.spring_boot_rest_app.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Account account) {
        repository.save(account);
        log.info("In AccountServiceImpl save {}", account);
    }

    @Override
    public void update(Long id, Account updatedAccount) {
        Account account = getById(id);

        if (updatedAccount.getStatus() != null) account.setStatus(updatedAccount.getStatus());

        repository.save(account);
        log.info("In AccountServiceImpl update {}", account);

    }

    @Override
    public void delete(Long id) {
        Account account = getById(id);
        log.info("In AccountServiceImpl delete {}", account);
        repository.delete(account);
    }

    @Override
    public Account getById(Long id) {
        Account result = repository.getOne(id);
        log.info("In AccountServiceImpl getById - account {} found by id: {}", result, result.getId());

        return result;
    }

    @Override
    public List<Account> list() {
        log.info("In AccountServiceImpl list");

        return repository.findAll();
    }
}
