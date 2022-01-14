package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.repository.OrderRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest//allows using added DB for testing
@Rollback//by default is true - rollback all changes in DB
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//uses the same DB
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//creates order for executing testing methods
@ExtendWith(MockitoExtension.class)//allows using @Mock
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    @Order(1)
    void save() {
        com.app.spring_boot_rest_app.entity.Order savedOrder =
                new com.app.spring_boot_rest_app.entity.Order("test");

        orderService.save(savedOrder);
        assertEquals(savedOrder, orderRepository.findById(savedOrder.getId()));
    }

    @Test
    @Order(2)
    void getById() {
    }

    @Test
    @Order(3)
    void update() {
    }

    @Test
    @Order(4)
    void list() {
    }

    @Test
    @Order(5)
    void delete() {
    }
}