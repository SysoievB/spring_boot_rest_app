package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.entity.Order;
import com.app.spring_boot_rest_app.repository.OrderRepository;
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
class OrderServiceImplTest {

    @Mock
    private OrderRepository underTestOrderRepository;

    @InjectMocks
    private OrderServiceImpl underTestOrderService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void itShouldSaveOrder() {
        //Given
        Order savedOrder = new Order("test");

        //When
        underTestOrderService.save(savedOrder);

        //Then
        assertNotNull(savedOrder);
        assertEquals("test", savedOrder.getOrderName());

        verify(underTestOrderRepository, atLeastOnce()).save(savedOrder);
    }

    @Test
    void itShouldGetByIdOrder() {
        //Given
        Order getOrder = new Order(1L, "test");

        //When
        when(underTestOrderRepository.getOne(getOrder.getId())).thenReturn(getOrder);

        //Then
        assertTrue(getOrder.getId() > 0);

        verify(underTestOrderRepository, atLeastOnce()).getOne(getOrder.getId());
    }

    @Test
    void itShouldListAllOrders() {
        //Given

        List<Order> orders = new ArrayList<>(Arrays.asList(new Order("test_list")));

        given(underTestOrderRepository.findAll()).willReturn(orders);

        //When
        List<Order> expected = underTestOrderService.list();

        //Then
        assertTrue(orders.size() > 0);
        assertEquals(expected, orders);

        verify(underTestOrderRepository, atLeastOnce()).findAll();
    }

    @Test
    void itShouldUpdateOrder() {
        //Given
        Order order = new Order(1L,"test");
        Order updatedOrder = new Order("updated");

        //When
        when(underTestOrderRepository.getOne(order.getId())).thenReturn(order);
        underTestOrderService.update(order.getId(),updatedOrder);

        //Then
        assertEquals("update", order.getOrderName());

        verify(underTestOrderRepository,atLeastOnce()).save(order);
    }

    @Test
    void itShouldDeleteOrderByIdIfFound() {
        //Given
        Order deletedOrder = new Order(1L, "test_delete");

        //When
        when(underTestOrderRepository.findById(deletedOrder.getId())).thenReturn(Optional.of(deletedOrder));

        //Then
        verify(underTestOrderRepository, atLeastOnce()).deleteById(deletedOrder.getId());
    }
}