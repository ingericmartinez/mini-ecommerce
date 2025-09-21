package com.ecommerce.api.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @Column(name = "ProductID")
    private Integer productId;

    @Column(name = "Name", length = 100)
    private String name;

    @Column(name = "Price", precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSupplier> productSuppliers = new ArrayList<>();

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public List<ProductSupplier> getProductSuppliers() { return productSuppliers; }
    public void setProductSuppliers(List<ProductSupplier> productSuppliers) { this.productSuppliers = productSuppliers; }
}
