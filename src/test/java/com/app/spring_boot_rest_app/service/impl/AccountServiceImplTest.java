package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.entity.Account;
import com.app.spring_boot_rest_app.entity.AccountStatus;
import com.app.spring_boot_rest_app.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository underTestAccountRepository;

    @InjectMocks
    private AccountServiceImpl underTestAccountService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void itShouldSaveAccount() {
        //Given
        Account testAccount = new Account(AccountStatus.ACTIVE);
        //When
        underTestAccountService.save(testAccount);

        //Then
        assertNotNull(testAccount);
        assertEquals(AccountStatus.ACTIVE, testAccount.getStatus());
    }

    @Test
    void itShouldUpdateAccount() {
        //Given
        //When
        //Then
    }

    @Test
    void itShouldDeleteAccount() {
        //Given
        //When
        //Then
    }

    @Test
    void itShouldGetAccountById() {
        //Given
        //When
        //Then
    }

    @Test
    void itShouldListAllAccounts() {
        //Given
        //When
        //Then
    }
}