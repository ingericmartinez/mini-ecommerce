package com.ecommerce.api.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductSupplierId implements Serializable {
    @Column(name = "ProductID")
    private Integer productId;

    @Column(name = "SupplierID")
    private Integer supplierId;

    public ProductSupplierId() {}
    public ProductSupplierId(Integer productId, Integer supplierId) {
        this.productId = productId;
        this.supplierId = supplierId;
    }

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSupplierId that = (ProductSupplierId) o;
        return Objects.equals(productId, that.productId) && Objects.equals(supplierId, that.supplierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, supplierId);
    }
}
