package com.ecommerce.api.service;

import com.ecommerce.api.model.dto.ProductDtos;
import java.util.List;

public interface ProductService {
    List<ProductDtos.ProductResponse> listAll();
    ProductDtos.ProductResponse getById(Integer id);
    ProductDtos.ProductResponse create(ProductDtos.ProductInput input);
}
