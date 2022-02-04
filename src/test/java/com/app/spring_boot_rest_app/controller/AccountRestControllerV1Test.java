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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    void get() {
        //Given
        //When
        //Then
    }

    @Test
    void save() {
        //Given
        //When
        //Then
    }

    @Test
    void update() {
        //Given
        //When
        //Then
    }

    @Test
    void delete() {
        //Given
        //When
        //Then
    }
}