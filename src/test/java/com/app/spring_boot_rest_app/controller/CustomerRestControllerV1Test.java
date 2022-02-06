package com.app.spring_boot_rest_app.controller;

import com.app.spring_boot_rest_app.entity.Customer;
import com.app.spring_boot_rest_app.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CustomerRestControllerV1Test {

    @Mock
    private CustomerServiceImpl underTestingCustomerService;

    @InjectMocks
    private CustomerRestControllerV1 underTestingCustomerRestControllerV1;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void itShouldGetAllCustomers() {
        // Given
        List<Customer> customers = new ArrayList<>(Arrays.asList(
                new Customer(1L, "name1", "surname1"),
                new Customer(2L, "name2", "surname2")
        ));

        // When
        when(underTestingCustomerService.list()).thenReturn(customers);

        ResponseEntity<List<Customer>> response = underTestingCustomerRestControllerV1.getAll();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(customers.get(0).getId(), response.getBody().get(0).getId());
        assertEquals(customers.get(1).getId(), response.getBody().get(1).getId());
    }

    @Test
    void itShouldGetAllCustomersFail() {
        // Given
        // When
        when(underTestingCustomerService.list()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Customer>> response = underTestingCustomerRestControllerV1.getAll();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void itShouldGetCustomer() {
        //Given
        Customer customer = new Customer(1L, "test", "test");

        //When
        when(underTestingCustomerService.getById(customer.getId())).thenReturn(customer);
        ResponseEntity<Customer> response = underTestingCustomerRestControllerV1.get(customer.getId());

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer.getId(), Objects.requireNonNull(response.getBody()).getId());
        assertEquals(customer.getName(), Objects.requireNonNull(response.getBody()).getName());
        assertEquals(customer.getSurname(), Objects.requireNonNull(response.getBody()).getSurname());
    }

    @Test
    public void itShouldGetCustomerFail() {
        // Given
        Long id = 1L;

        // When
        ResponseEntity<Customer> response1 = underTestingCustomerRestControllerV1.get(null);

        when(underTestingCustomerService.getById(id)).thenReturn(null);
        ResponseEntity<Customer> response2 = underTestingCustomerRestControllerV1.get(id);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response1.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    @Test
    void itShouldSaveCustomer() {
        // Given
        Customer customer = new Customer(1L, "test", "test");

        // When
        ResponseEntity<Customer> response = underTestingCustomerRestControllerV1.save(customer);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer.getId(), Objects.requireNonNull(response.getBody()).getId());
        assertEquals(customer.getName(), Objects.requireNonNull(response.getBody()).getName());
        assertEquals(customer.getSurname(), Objects.requireNonNull(response.getBody()).getSurname());

        verify(underTestingCustomerService, atLeastOnce()).save(customer);
    }

    @Test
    void itShouldSaveCustomerFail() {
        // Given
        // When
        ResponseEntity<Customer> response = underTestingCustomerRestControllerV1.save(null);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        verify(underTestingCustomerService, never()).save(any(Customer.class));
    }

    @Test
    void itShouldUpdateCustomer() {
        // Given
        Long customerId = 1L;
        var customer = new Customer(customerId, "test","test");

        // When
        ResponseEntity<Customer> response = underTestingCustomerRestControllerV1.update(customerId, customer);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(customer.getId(), Objects.requireNonNull(response.getBody()).getId());
        assertEquals(customer.getName(), Objects.requireNonNull(response.getBody()).getName());
        assertEquals(customer.getSurname(), Objects.requireNonNull(response.getBody()).getSurname());

        verify(underTestingCustomerService, atLeastOnce()).update(customerId, customer);
    }

    @Test
    void itShouldUpdateCustomerFail() {
        // Given
        // When
        ResponseEntity<Customer> response = underTestingCustomerRestControllerV1.update(null, null);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        verify(underTestingCustomerService, never()).update(anyLong(), any(Customer.class));
    }

    @Test
    void itShouldDeleteCustomer() {
        // Given
        var customer = new Customer(1L, "test","test");

        // When
        when(underTestingCustomerService.getById(customer.getId())).thenReturn(customer);

        ResponseEntity<Customer> response = underTestingCustomerRestControllerV1.delete(customer.getId());

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

        verify(underTestingCustomerService, atLeastOnce()).delete(customer.getId());
    }

    @Test
    void itShouldDeleteCustomerFail() {
        // Given
        Long id = 1L;

        // When
        when(underTestingCustomerService.getById(id)).thenReturn(null);

        ResponseEntity<Customer> response = underTestingCustomerRestControllerV1.delete(id);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(underTestingCustomerService, never()).delete(anyLong());
    }
}