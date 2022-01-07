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
import java.util.Optional;
import java.util.Set;

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
        Set<Order> orders = new HashSet<>();

        for (var order : customer.getOrders()) {
            Optional<Order> optionalOrder = orderRepository.findById(order.getId());
            Order customerOrder = optionalOrder.get();
            orders.add(customerOrder);
        }

        customer.setOrders(orders);
        customerRepository.save(customer);
        log.info("In CustomerServiceImpl save {}", customer);
    }

    @Override
    public void update(Long id, Customer customer) {

    }

    @Override
    public void delete(Long id) {
        Customer customer = getById(id);
        log.info("In CustomerServiceImpl delete {}", customer);
        customerRepository.delete(customer);
    }

    @Override
    public Customer getById(Long id) {
        Customer result = customerRepository.getOne(id);
        log.info("In CustomerServiceImpl getById - order {} found by id: {}", result, result.getId());

        return result;
    }

    @Override
    public List<Customer> list() {
        log.info("In CustomerServiceImpl list");

        return customerRepository.findAll();
    }
}
