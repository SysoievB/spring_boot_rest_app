package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.entity.Order;
import com.app.spring_boot_rest_app.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public void save(Order item) {

    }

    @Override
    public void update(Long id, Order item) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Order getById(Long id) {
        return null;
    }

    @Override
    public List<Order> list() {
        return null;
    }
}
