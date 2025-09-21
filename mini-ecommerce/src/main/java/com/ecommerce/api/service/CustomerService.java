package com.ecommerce.api.service;

import com.ecommerce.api.model.dto.CustomerDtos;
import com.ecommerce.api.model.dto.OrderDtos;
import java.util.List;

public interface CustomerService {
    List<CustomerDtos.CustomerResponse> listAll();
    CustomerDtos.CustomerResponse getById(Integer id);
    CustomerDtos.CustomerResponse create(CustomerDtos.CustomerInput input);
    List<OrderDtos.OrderResponse> getOrdersByCustomer(Integer customerId);
}
