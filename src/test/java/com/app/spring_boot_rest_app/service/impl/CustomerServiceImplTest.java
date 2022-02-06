package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.entity.Account;
import com.app.spring_boot_rest_app.entity.AccountStatus;
import com.app.spring_boot_rest_app.entity.Customer;
import com.app.spring_boot_rest_app.entity.Order;
import com.app.spring_boot_rest_app.repository.CustomerRepository;
import com.app.spring_boot_rest_app.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository underTestCustomerRepository;

    @Mock
    private OrderRepository underTestOrderRepository;

    @InjectMocks
    private CustomerServiceImpl underTestCustomerService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void itShouldSaveCustomer() {
        // Given
        var orderFirst = new Order(1L, "car");
        var orderSecond = new Order(2L, "moto");
        var orderThird = new Order(3L, "pizza");

        Set<Order> orders = new HashSet<>();
        orders.add(orderFirst);
        orders.add(orderSecond);
        orders.add(orderThird);

        Customer customer = new Customer(1L, "vasia", "pupkin", new Account(AccountStatus.ACTIVE), orders);

        // When
        when(underTestOrderRepository.findById(1L)).thenReturn(Optional.of(orderFirst));
        when(underTestOrderRepository.findById(2L)).thenReturn(Optional.of(orderSecond));
        when(underTestOrderRepository.findById(3L)).thenReturn(Optional.of(orderThird));

        underTestCustomerService.save(customer);

        // Then
        assertNotNull(customer);
        assertEquals("vasia", customer.getName());
        assertEquals("pupkin", customer.getSurname());
        assertEquals(AccountStatus.ACTIVE.name(), customer.getAccount().getStatus().name());

        verify(underTestCustomerRepository, atLeastOnce()).save(customer);

        for (var o : customer.getOrders()) {
            verify(underTestOrderRepository, atLeastOnce()).findById(o.getId());
        }
    }

    @Test
    void itShouldUpdateCustomer() {
        // Given
        Customer customer = new Customer(1L, "vasia", "pupkin");

        var order = new Order(1L, "car");

        Set<Order> orders = new HashSet<>();
        orders.add(order);

        Customer updatedCustomer = new Customer(1L, "petia", "petrov", new Account(AccountStatus.ACTIVE), orders);

        // When
        when(underTestOrderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(underTestCustomerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        underTestCustomerService.update(customer.getId(), updatedCustomer);

        // Then
        assertEquals(updatedCustomer.getId(), customer.getId());
        assertEquals(updatedCustomer.getName(), customer.getName());
        assertEquals(updatedCustomer.getSurname(), customer.getSurname());
        assertEquals(updatedCustomer.getAccount().getStatus().name(), customer.getAccount().getStatus().name());
        assertEquals(updatedCustomer.getOrders().size(), customer.getOrders().size());

        verify(underTestCustomerRepository, atLeastOnce()).save(customer);
        verify(underTestCustomerRepository, atLeastOnce()).findById(customer.getId());
    }

    @Test
    void itShouldDeleteCustomer() {
        //Given
        Customer deletedCustomer = new Customer(1L, "vasia", "pupkin");

        //When
        when(underTestCustomerRepository.findById(deletedCustomer.getId())).thenReturn(Optional.of(deletedCustomer));
        underTestCustomerService.delete(deletedCustomer.getId());

        //Then
        verify(underTestCustomerRepository, atLeastOnce()).delete(deletedCustomer);
    }

    @Test
    void itShouldGetByIdCustomer() {
        // Given
        Customer customer = new Customer(1L, "vasia", "pupkin");

        // When
        when(underTestCustomerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        underTestCustomerService.getById(customer.getId());

        // Then
        assertEquals(1L, customer.getId());

        verify(underTestCustomerRepository, atLeastOnce()).findById(customer.getId());
    }

    @Test
    void itShouldListCustomers() {
        // Given
        List<Customer> customers = new ArrayList<>(Arrays.asList(
                new Customer(1L, "vasia", "pupkin"),
                new Customer(2L, "petia", "pettrov")
        ));

        // When
        when(underTestCustomerRepository.findAll()).thenReturn(customers);

        List<Customer> actualResultList = underTestCustomerService.list();

        // Then
        assertEquals(customers.size(), actualResultList.size());

        verify(underTestCustomerRepository, atLeastOnce()).findAll();
    }
}