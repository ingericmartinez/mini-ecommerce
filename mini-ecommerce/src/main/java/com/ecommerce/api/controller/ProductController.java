package com.ecommerce.api.controller;

import com.ecommerce.api.model.dto.ProductDtos;
import com.ecommerce.api.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ecommerce/products")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDtos.ProductResponse> list() {
        log.info("GET /api/v1/ecommerce/products");
        return productService.listAll();
    }

    @GetMapping("/{id}")
    public ProductDtos.ProductResponse get(@PathVariable Integer id) {
        log.info("GET /api/v1/ecommerce/products/{}", id);
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDtos.ProductResponse create(@Valid @RequestBody ProductDtos.ProductInput input) {
        log.info("POST /api/v1/ecommerce/products");
        return productService.create(input);
    }
}
