package com.ecommerce.api.mapper;

import com.ecommerce.api.model.dto.*;
import com.ecommerce.api.model.entity.*;

import java.math.BigDecimal;

public class EcommerceMapper {
    public static Product toEntity(ProductDtos.ProductInput dto, Category category, Integer id) {
        Product p = new Product();
        p.setProductId(id);
        p.setName(dto.name);
        p.setPrice(dto.price);
        p.setCategory(category);
        return p;
    }
    public static ProductDtos.ProductResponse toDto(Product entity) {
        ProductDtos.ProductResponse dto = new ProductDtos.ProductResponse();
        dto.productId = entity.getProductId();
        dto.name = entity.getName();
        dto.price = entity.getPrice();
        dto.categoryId = entity.getCategory() != null ? entity.getCategory().getCategoryId() : null;
        return dto;
    }

    public static Customer toEntity(CustomerDtos.CustomerInput dto, Integer id) {
        Customer c = new Customer();
        c.setCustomerId(id);
        c.setName(dto.name);
        c.setEmail(dto.email);
        return c;
    }
    public static CustomerDtos.CustomerResponse toDto(Customer entity) {
        CustomerDtos.CustomerResponse dto = new CustomerDtos.CustomerResponse();
        dto.customerId = entity.getCustomerId();
        dto.name = entity.getName();
        dto.email = entity.getEmail();
        return dto;
    }

    public static Category toEntity(CategoryDtos.CategoryInput dto, Integer id) {
        Category c = new Category();
        c.setCategoryId(id);
        c.setCategoryName(dto.categoryName);
        return c;
    }
    public static CategoryDtos.CategoryResponse toDto(Category entity) {
        CategoryDtos.CategoryResponse dto = new CategoryDtos.CategoryResponse();
        dto.categoryId = entity.getCategoryId();
        dto.categoryName = entity.getCategoryName();
        return dto;
    }

    public static Supplier toEntity(SupplierDtos.SupplierInput dto, Integer id) {
        Supplier s = new Supplier();
        s.setSupplierId(id);
        s.setSupplierName(dto.supplierName);
        s.setContactEmail(dto.contactEmail);
        return s;
    }
    public static SupplierDtos.SupplierResponse toDto(Supplier entity) {
        SupplierDtos.SupplierResponse dto = new SupplierDtos.SupplierResponse();
        dto.supplierId = entity.getSupplierId();
        dto.supplierName = entity.getSupplierName();
        dto.contactEmail = entity.getContactEmail();
        return dto;
    }

    public static Order toEntity(OrderDtos.OrderInput dto, Integer id, Customer customer) {
        Order o = new Order();
        o.setOrderId(id);
        o.setCustomer(customer);
        o.setOrderDate(dto.orderDate);
        return o;
    }
    public static OrderDtos.OrderResponse toDto(Order entity) {
        OrderDtos.OrderResponse dto = new OrderDtos.OrderResponse();
        dto.orderId = entity.getOrderId();
        dto.customerId = entity.getCustomer() != null ? entity.getCustomer().getCustomerId() : null;
        dto.orderDate = entity.getOrderDate();
        return dto;
    }

    public static OrderItem toEntity(Order order, Product product, Integer quantity, Integer orderItemId) {
        OrderItem oi = new OrderItem();
        oi.setOrderItemId(orderItemId);
        oi.setOrder(order);
        oi.setProduct(product);
        oi.setQuantity(quantity);
        return oi;
    }
}
