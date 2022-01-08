package com.app.spring_boot_rest_app.controller;

import com.app.spring_boot_rest_app.entity.Account;
import com.app.spring_boot_rest_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountRestControllerV1 {

    private final AccountService accountService;

    @Autowired
    public AccountRestControllerV1(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        var accountList = this.accountService.list();

        if (accountList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(accountList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> get(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        var account = this.accountService.getById(id);

        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody Account account) {

        if (account == null) {
            return ResponseEntity.badRequest().build();
        }

        this.accountService.save(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {

        if (id == null || account == null) {
            return ResponseEntity.badRequest().build();
        }

        this.accountService.update(id, account);

        return ResponseEntity.accepted().body(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Account> delete(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        var account = this.accountService.getById(id);

        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        this.accountService.delete(id);

        return ResponseEntity.accepted().build();
    }
}
