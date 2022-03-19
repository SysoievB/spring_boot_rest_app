package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.entity.Order;
import com.app.spring_boot_rest_app.repository.OrderRepository;
import com.app.spring_boot_rest_app.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Order order) {
        repository.save(order);
        log.info("In OrderServiceImpl save {}", order);
    }

    @Override
    public void update(Long id, Order updatedOrder) {
        Order order = getById(id);

        if (updatedOrder.getOrderName() != null) order.setOrderName(updatedOrder.getOrderName());

        repository.save(order);
        log.info("In OrderServiceImpl update {}", order);
    }

    @Override
    public void delete(Long id) {
        Order order = getById(id);
        log.info("In OrderServiceImpl delete {}", order);
        repository.delete(order);
    }

    @Override
    public Order getById(Long id) {
        Order result = repository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);

        log.info("In OrderServiceImpl getById - order {} found by id: {}", result, result.getId());

        return result;
    }

    @Override
    public List<Order> list() {
        log.info("In OrderServiceImpl list");

        return repository.findAll();
    }
}
