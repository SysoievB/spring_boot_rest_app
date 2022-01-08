package com.app.spring_boot_rest_app.controller;

import com.app.spring_boot_rest_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerRestControllerV1 {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestControllerV1(CustomerService customerService) {
        this.customerService = customerService;
    }
}
