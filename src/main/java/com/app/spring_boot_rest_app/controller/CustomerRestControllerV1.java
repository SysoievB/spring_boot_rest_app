package com.app.spring_boot_rest_app.controller;

import com.app.spring_boot_rest_app.entity.Customer;
import com.app.spring_boot_rest_app.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerRestControllerV1 {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        var customerList = this.customerService.list();

        if (customerList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        var customer = this.customerService.getById(id);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {

        if (customer == null) {
            return ResponseEntity.badRequest().build();
        }

        this.customerService.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {

        if (id == null || customer == null) {
            return ResponseEntity.badRequest().build();
        }

        this.customerService.update(id, customer);

        return ResponseEntity.accepted().body(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        var customer = this.customerService.getById(id);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        this.customerService.delete(id);

        return ResponseEntity.accepted().build();
    }
}
