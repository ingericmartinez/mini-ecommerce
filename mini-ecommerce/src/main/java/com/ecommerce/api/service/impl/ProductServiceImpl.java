package com.ecommerce.api.service.impl;

import com.ecommerce.api.exception.ResourceNotFoundException;
import com.ecommerce.api.mapper.EcommerceMapper;
import com.ecommerce.api.model.dto.ProductDtos;
import com.ecommerce.api.model.entity.Category;
import com.ecommerce.api.model.entity.Product;
import com.ecommerce.api.repository.CategoryRepository;
import com.ecommerce.api.repository.ProductRepository;
import com.ecommerce.api.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDtos.ProductResponse> listAll() {
        log.debug("Listing all products");
        return productRepository.findAll().stream().map(EcommerceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDtos.ProductResponse getById(Integer id) {
        log.debug("Fetching product with id {}", id);
        Product p = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        return EcommerceMapper.toDto(p);
    }

    @Override
    public ProductDtos.ProductResponse create(ProductDtos.ProductInput input) {
        log.debug("Creating product: {}", input.name);
        Category category = categoryRepository.findById(input.categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        int nextId = productRepository.findAll().stream().map(Product::getProductId).max(Comparator.naturalOrder()).orElse(0) + 1;
        Product entity = EcommerceMapper.toEntity(input, category, nextId);
        Product saved = productRepository.save(entity);
        return EcommerceMapper.toDto(saved);
    }
}
