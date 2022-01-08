package com.app.spring_boot_rest_app.controller;

import com.app.spring_boot_rest_app.entity.Order;
import com.app.spring_boot_rest_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestControllerV1 {

    private final OrderService orderService;

    @Autowired
    public OrderRestControllerV1(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        var orderList = this.orderService.list();

        if (orderList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        var order = this.orderService.getById(id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {

        if (order == null) {
            return ResponseEntity.badRequest().build();
        }

        this.orderService.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order) {

        if (id == null || order == null) {
            return ResponseEntity.badRequest().build();
        }

        this.orderService.update(id, order);

        return ResponseEntity.accepted().body(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Order order = this.orderService.getById(id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        this.orderService.delete(id);

        return ResponseEntity.accepted().build();
    }
}
