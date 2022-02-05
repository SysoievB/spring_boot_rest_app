package com.app.spring_boot_rest_app.controller;

import com.app.spring_boot_rest_app.entity.Account;
import com.app.spring_boot_rest_app.entity.AccountStatus;
import com.app.spring_boot_rest_app.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountRestControllerV1Test {

    @Mock
    private AccountServiceImpl underTestingAccountService;

    @InjectMocks
    private AccountRestControllerV1 underTestingAccountRestControllerV1;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void itShouldGetAllAccounts() {
        //Given
        List<Account> accounts = new ArrayList<>(Arrays.asList(
                new Account(AccountStatus.ACTIVE),
                new Account(AccountStatus.BANNED)
        ));
        //When
        when(underTestingAccountService.list()).thenReturn(accounts);

        var response = underTestingAccountRestControllerV1.getAll();

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accounts.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(accounts.get(0).getId(), response.getBody().get(0).getId());
        assertEquals(accounts.get(1).getId(), response.getBody().get(1).getId());
    }

    @Test
    void itShouldGetAllAccountsFail() {
        // Given
        // When
        when(underTestingAccountService.list()).thenReturn(new ArrayList<>());

        var response = underTestingAccountRestControllerV1.getAll();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void itShouldGetAccount() {
        //Given
        Account account = new Account(1L, AccountStatus.ACTIVE);

        //When
        when(underTestingAccountService.getById(account.getId())).thenReturn(account);
        ResponseEntity<Account> response = underTestingAccountRestControllerV1.get(account.getId());

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(account.getId(), Objects.requireNonNull(response.getBody()).getId());
        assertEquals(account.getStatus().name(), Objects.requireNonNull(response.getBody()).getStatus().name());
    }

    @Test
    void itShouldGetAccountFail() {
        // Given
        Long id = 1L;

        // When
        ResponseEntity<Account> response1 = underTestingAccountRestControllerV1.get(null);

        when(underTestingAccountService.getById(id)).thenReturn(null);
        ResponseEntity<Account> response2 = underTestingAccountRestControllerV1.get(id);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response1.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    @Test
    void itShouldSaveAccount() {
        // Given
        Account account = new Account(AccountStatus.ACTIVE);

        // When
        ResponseEntity<Account> response = underTestingAccountRestControllerV1.save(account);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(account.getStatus().name(), Objects.requireNonNull(response.getBody()).getStatus().name());

        verify(underTestingAccountService, atLeastOnce()).save(account);
    }

    @Test
    void itShouldSaveAccountFail() {
        // Given
        // When
        ResponseEntity<Account> response = underTestingAccountRestControllerV1.save(null);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        verify(underTestingAccountService, never()).save(any(Account.class));
    }

    @Test
    void itShouldUpdateAccount() {
        // Given
        Long accountId = 1L;
        Account account = new Account(accountId, AccountStatus.ACTIVE);

        // When
        ResponseEntity<Account> response = underTestingAccountRestControllerV1.update(accountId, account);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(account.getStatus().name(), Objects.requireNonNull(response.getBody()).getStatus().name());

        verify(underTestingAccountService, atLeastOnce()).update(accountId, account);
    }

    @Test
    void itShouldUpdateAccountFail() {
        // Given
        // When
        ResponseEntity<Account> response = underTestingAccountRestControllerV1.update(null, null);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        verify(underTestingAccountService, never()).update(anyLong(), any(Account.class));
    }

    @Test
    void itShouldDeleteAccount() {
        // Given
        var account = new Account(1L, AccountStatus.ACTIVE);

        // When
        when(underTestingAccountService.getById(account.getId())).thenReturn(account);

        ResponseEntity<Account> response = underTestingAccountRestControllerV1.delete(account.getId());

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

        verify(underTestingAccountService, times(1)).delete(account.getId());
    }

    @Test
    void itShouldDeleteAccountFail() {
        // Given
        Long id = 1L;

        // When
        when(underTestingAccountService.getById(id)).thenReturn(null);

        ResponseEntity<Account> response = underTestingAccountRestControllerV1.delete(id);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(underTestingAccountService, never()).delete(anyLong());
    }
}