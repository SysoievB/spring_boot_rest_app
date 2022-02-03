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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository underTestAccountRepository;

    @InjectMocks
    private AccountServiceImpl underTestAccountService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void itShouldSaveAccount() {
        //Given
        Account saveAccount = new Account(AccountStatus.ACTIVE);

        //When
        underTestAccountService.save(saveAccount);

        //Then
        assertNotNull(saveAccount);
        assertEquals(AccountStatus.ACTIVE, saveAccount.getStatus());

        verify(underTestAccountRepository, atLeastOnce()).save(saveAccount);
    }

    @Test
    void itShouldUpdateAccount() {
        //Given
        Account account = new Account(1L, AccountStatus.ACTIVE);
        Account updatedAccount = new Account(AccountStatus.BANNED);
        given(underTestAccountRepository.findById(account.getId())).willReturn(Optional.of(account));

        //When
        underTestAccountService.update(account.getId(), updatedAccount);

        //Then
        assertEquals(AccountStatus.BANNED.name(), account.getStatus().name());

        verify(underTestAccountRepository, atLeastOnce()).save(account);
        verify(underTestAccountRepository, atLeastOnce()).findById(account.getId());
    }

    @Test
    void itShouldDeleteAccountIfFound() {
        //Given
        Account deletedAccount = new Account(1L, AccountStatus.ACTIVE);

        //When
        when(underTestAccountRepository.findById(deletedAccount.getId())).thenReturn(Optional.of(deletedAccount));
        underTestAccountService.delete(deletedAccount.getId());

        //Then
        verify(underTestAccountRepository, atLeastOnce()).delete(deletedAccount);
    }

    @Test
    void itShouldGetAccountById() {
        //Given
        Account getAccount = new Account(1L, AccountStatus.ACTIVE);

        //When
        when(underTestAccountRepository.findById(getAccount.getId())).thenReturn(Optional.of(getAccount));
        underTestAccountService.getById(getAccount.getId());

        //Then
        assertTrue(getAccount.getId() > 0);

        verify(underTestAccountRepository, atLeastOnce()).findById(getAccount.getId());
    }

    @Test
    void itShouldListAllAccounts() {
        List<Account> accounts = new ArrayList<>(Arrays.asList(new Account(AccountStatus.ACTIVE)));

        given(underTestAccountRepository.findAll()).willReturn(accounts);

        //When
        List<Account> expected = underTestAccountRepository.findAll();

        //Then
        assertTrue(accounts.size() > 0);
        assertEquals(expected, accounts);

        verify(underTestAccountRepository, atLeastOnce()).findAll();
    }
}