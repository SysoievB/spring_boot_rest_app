package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.repository.OrderRepository;
import com.app.spring_boot_rest_app.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*@Slf4j
@DataJpaTest//allows using added DB for testing
//@Rollback(value = false)//by default is true - rollback all changes in DB
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//uses the same DB
@TestPropertySource(
        locations = "classpath:application-integration-test.properties")

@ExtendWith(MockitoExtension.class)//allows using @Mock*/
@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//creates order for executing testing methods
@RunWith(MockitoJUnitRunner.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository underTestOrderRepository;

    @InjectMocks
    private OrderServiceImpl underTestOrderService;

    @Test
    @Order(1)
    @Rollback(value = false)
    void itShouldSave() {
        //Given
        com.app.spring_boot_rest_app.entity.Order savedOrder =
                new com.app.spring_boot_rest_app.entity.Order("test");
        //When
        underTestOrderService.save(savedOrder);
        //Then
        assertNotNull(savedOrder);
        assertEquals("test", savedOrder.getOrderName());
    }

    @Test
    @Order(2)
    void itShouldGetById() {
        //Given
        /*com.app.spring_boot_rest_app.entity.Order orderGet = underTestOrderService
                .list()
                .stream()
                .filter(o->o.getOrderName().equals("test"))
                .findFirst().get();*/
        //When
        //underTestOrderService.getById(order.getId());
        //Then
       // assertTrue(orderGet.getId() > 0);
       // assertEquals((13L), (long) orderGet.getId());
    }

    @Test
    @Order(3)
    void itShouldList() {
        //Given
        List<com.app.spring_boot_rest_app.entity.Order> orders = underTestOrderService.list();

        //When

        //Then
        assertTrue(orders.size()>0);
    }
    @Test
    @Order(4)
    @Rollback(value = false)
    void itShouldUpdate() {
        //Given
        //When
        //Then
    }

    @Test
    @Order(5)
    void itShouldDelete() {
        //Given
        //When
        //Then
    }
}