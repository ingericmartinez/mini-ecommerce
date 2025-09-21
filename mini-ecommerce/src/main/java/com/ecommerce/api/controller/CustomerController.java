package com.ecommerce.api.controller;

import com.ecommerce.api.model.dto.CustomerDtos;
import com.ecommerce.api.model.dto.OrderDtos;
import com.ecommerce.api.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ecommerce/customers")
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDtos.CustomerResponse> list() {
        log.info("GET /api/v1/ecommerce/customers");
        return customerService.listAll();
    }

    @GetMapping("/{id}")
    public CustomerDtos.CustomerResponse get(@PathVariable Integer id) {
        log.info("GET /api/v1/ecommerce/customers/{}", id);
        return customerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDtos.CustomerResponse create(@Valid @RequestBody CustomerDtos.CustomerInput input) {
        log.info("POST /api/v1/ecommerce/customers");
        return customerService.create(input);
    }

    @GetMapping("/{id}/orders")
    public List<OrderDtos.OrderResponse> getOrders(@PathVariable("id") Integer customerId) {
        log.info("GET /api/v1/ecommerce/customers/{}/orders", customerId);
        return customerService.getOrdersByCustomer(customerId);
    }
}
