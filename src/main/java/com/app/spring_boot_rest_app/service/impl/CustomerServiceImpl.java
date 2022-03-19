package com.app.spring_boot_rest_app.service.impl;

import com.app.spring_boot_rest_app.entity.Customer;
import com.app.spring_boot_rest_app.entity.Order;
import com.app.spring_boot_rest_app.repository.CustomerRepository;
import com.app.spring_boot_rest_app.repository.OrderRepository;
import com.app.spring_boot_rest_app.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void save(Customer customer) {
        Set<Order> orders = customer.getOrders()
                .stream()
                .map(
                        order -> orderRepository
                                .findById(order.getId())
                                .orElseThrow(NoSuchElementException::new)
                )
                .collect(Collectors.toCollection(HashSet::new));

        customer.setOrders(orders);
        customerRepository.save(customer);
        log.info("In CustomerServiceImpl save {}", customer);
    }

    @Override
    public void update(Long id, Customer updatedCustomer) {
        Customer customer = getById(id);

        if (updatedCustomer.getName() != null) customer.setName(updatedCustomer.getName());

        if (updatedCustomer.getSurname() != null) customer.setSurname(updatedCustomer.getSurname());

        if (updatedCustomer.getAccount() != null) customer.setAccount(updatedCustomer.getAccount());

        if (updatedCustomer.getOrders() != null) customer.setOrders(updatedCustomer.getOrders());

        customerRepository.save(customer);

        log.info("In CustomerServiceImpl update {}", customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = getById(id);
        log.info("In CustomerServiceImpl delete {}", customer);
        customerRepository.delete(customer);
    }

    @Override
    public Customer getById(Long id) {
        Customer result = customerRepository.findById(id).orElseThrow();
        log.info("In CustomerServiceImpl getById - order {} found by id: {}", result, result.getId());

        return result;
    }

    @Override
    public List<Customer> list() {
        log.info("In CustomerServiceImpl list");

        return customerRepository.findAll();
    }
}
