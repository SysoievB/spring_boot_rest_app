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

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        // When
        // Then
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
        // When
        // Then
    }

    @Test
    void itShouldUpdateOrder() {
        // Given
        // When
        // Then
    }

    @Test
    void itShouldDeleteOrder() {
        // Given
        // When
        // Then
    }
}