package com.app.spring_boot_rest_app.controller;

import com.app.spring_boot_rest_app.entity.Order;
import com.app.spring_boot_rest_app.service.impl.OrderServiceImpl;

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
class OrderRestControllerV1Test {

    @Mock
    private OrderServiceImpl underTestingOrderService;

    @InjectMocks
    private OrderRestControllerV1 underTestingOrderRestControllerV1;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void itShouldGetAllOrders() {
        // Given
        List<Order> orders = new ArrayList<>(Arrays.asList(
                new Order("first"),
                new Order("second")
        ));

        // When
        when(underTestingOrderService.list()).thenReturn(orders);

        ResponseEntity<List<Order>> response = underTestingOrderRestControllerV1.getAll();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(orders.get(0).getId(), response.getBody().get(0).getId());
        assertEquals(orders.get(1).getId(), response.getBody().get(1).getId());
    }

    @Test
    void itShouldGetAllOrdersFail() {
        // Given
        // When
        when(underTestingOrderService.list()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Order>> response = underTestingOrderRestControllerV1.getAll();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void itShouldGetOrder() {
        //Given
        Order order = new Order(1L, "test");

        //When
        when(underTestingOrderService.getById(order.getId())).thenReturn(order);
        ResponseEntity<Order> response = underTestingOrderRestControllerV1.get(order.getId());

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order.getId(), Objects.requireNonNull(response.getBody()).getId());
        assertEquals(order.getOrderName(), Objects.requireNonNull(response.getBody()).getOrderName());
    }

    @Test
    public void itShouldGetOrderFail() {
        // Given
        Long id = 1L;

        // When
        ResponseEntity<Order> response1 = underTestingOrderRestControllerV1.get(null);

        when(underTestingOrderService.getById(id)).thenReturn(null);
        ResponseEntity<Order> response2 = underTestingOrderRestControllerV1.get(id);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response1.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    @Test
    void itShouldSaveOrder() {
        // Given
        Order order = new Order("test");

        // When
        ResponseEntity<Order> response = underTestingOrderRestControllerV1.save(order);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order.getOrderName(), Objects.requireNonNull(response.getBody()).getOrderName());

        verify(underTestingOrderService, atLeastOnce()).save(order);
    }

    @Test
    void itShouldSaveOrderFail() {
        // Given
        // When
        ResponseEntity<Order> response = underTestingOrderRestControllerV1.save(null);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        verify(underTestingOrderService, never()).save(any(Order.class));
    }

    @Test
    void itShouldUpdateOrder() {
        // Given
        Long orderId = 1L;
        var order = new Order(orderId, "test");

        // When
        ResponseEntity<Order> response = underTestingOrderRestControllerV1.update(orderId, order);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(order.getOrderName(), Objects.requireNonNull(response.getBody()).getOrderName());

        verify(underTestingOrderService, atLeastOnce()).update(orderId, order);
    }

    @Test
    void itShouldUpdateOrderFail() {
        // Given
        // When
        ResponseEntity<Order> response = underTestingOrderRestControllerV1.update(null, null);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        verify(underTestingOrderService, never()).update(anyLong(), any(Order.class));
    }

    @Test
    void itShouldDeleteOrder() {
        // Given
        var order = new Order(1L, "test");

        // When
        when(underTestingOrderService.getById(order.getId())).thenReturn(order);

        ResponseEntity<Order> response = underTestingOrderRestControllerV1.delete(order.getId());

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

        verify(underTestingOrderService, times(1)).delete(order.getId());
    }

    @Test
    void itShouldDeleteOrderFail() {
        // Given
        Long id = 1L;

        // When
        when(underTestingOrderService.getById(id)).thenReturn(null);

        ResponseEntity<Order> response = underTestingOrderRestControllerV1.delete(id);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(underTestingOrderService, never()).delete(anyLong());
    }
}