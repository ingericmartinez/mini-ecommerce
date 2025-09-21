package com.ecommerce.api.repository;

import com.ecommerce.api.model.entity.ProductSupplier;
import com.ecommerce.api.model.entity.ProductSupplierId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, ProductSupplierId> {
    List<ProductSupplier> findByProduct_ProductId(Integer productId);
}
