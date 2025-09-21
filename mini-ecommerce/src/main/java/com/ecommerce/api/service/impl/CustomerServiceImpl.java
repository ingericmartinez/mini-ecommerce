package com.ecommerce.api.service.impl;

import com.ecommerce.api.exception.ResourceNotFoundException;
import com.ecommerce.api.mapper.EcommerceMapper;
import com.ecommerce.api.model.dto.CustomerDtos;
import com.ecommerce.api.model.dto.OrderDtos;
import com.ecommerce.api.model.entity.Customer;
import com.ecommerce.api.model.entity.Order;
import com.ecommerce.api.repository.CustomerRepository;
import com.ecommerce.api.repository.OrderRepository;
import com.ecommerce.api.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDtos.CustomerResponse> listAll() {
        log.debug("Listing all customers");
        return customerRepository.findAll().stream().map(EcommerceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDtos.CustomerResponse getById(Integer id) {
        log.debug("Fetching customer {}", id);
        Customer c = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        return EcommerceMapper.toDto(c);
    }

    @Override
    public CustomerDtos.CustomerResponse create(CustomerDtos.CustomerInput input) {
        log.debug("Creating customer: {}", input.email);
        int nextId = customerRepository.findAll().stream().map(Customer::getCustomerId).max(Comparator.naturalOrder()).orElse(0) + 1;
        Customer entity = EcommerceMapper.toEntity(input, nextId);
        return EcommerceMapper.toDto(customerRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDtos.OrderResponse> getOrdersByCustomer(Integer customerId) {
        log.debug("Listing orders for customer {}", customerId);
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
        return orderRepository.findByCustomer_CustomerId(customerId).stream().map(EcommerceMapper::toDto).collect(Collectors.toList());
    }
}
