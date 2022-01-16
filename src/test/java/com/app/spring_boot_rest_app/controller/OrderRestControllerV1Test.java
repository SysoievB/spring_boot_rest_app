package com.app.spring_boot_rest_app.controller;

import com.app.spring_boot_rest_app.entity.Order;
import com.app.spring_boot_rest_app.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Test
    void itShouldGetAll() {
    }

    @Test
    void itShouldGet() {
        //Given
        Order order = new Order("test");

        //When
        when(underTestingOrderService.getById(order.getId())).thenReturn(order);
        ResponseEntity<Order> response = underTestingOrderRestControllerV1.get(order.getId());

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order.getId(), Objects.requireNonNull(response.getBody()).getId());
        assertEquals(order.getOrderName(), Objects.requireNonNull(response.getBody()).getOrderName());
    }

    @Test
    void itShouldSave() {
    }

    @Test
    void itShouldUpdate() {
    }

    @Test
    void itShouldDelete() {
    }
}