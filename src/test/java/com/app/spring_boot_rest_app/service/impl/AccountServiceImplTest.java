package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.entity.Account;
import com.app.spring_boot_rest_app.entity.AccountStatus;
import com.app.spring_boot_rest_app.repository.AccountRepository;
import com.app.spring_boot_rest_app.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
class AccountServiceImplTest {

    private MockMvc mockMvc;

    @Mock
    private AccountRepository underTestAccountRepository;

    @InjectMocks
    private AccountService underTestAccountService;

   /* @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTestAccountService).build();
    }*/

    @Test
    void itShouldSaveAccount() {
        //Given
        Account testAccount = new Account();
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