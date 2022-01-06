package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.entity.Account;
import com.app.spring_boot_rest_app.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Override
    public void save(Account item) {

    }

    @Override
    public void update(Long id, Account item) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Account getById(Long id) {
        return null;
    }

    @Override
    public List<Account> list() {
        return null;
    }
}
