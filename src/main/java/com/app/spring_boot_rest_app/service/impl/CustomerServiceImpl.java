package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.entity.Customer;
import com.app.spring_boot_rest_app.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void save(Customer item) {

    }

    @Override
    public void update(Long id, Customer item) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public List<Customer> list() {
        return null;
    }
}
