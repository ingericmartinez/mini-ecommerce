package com.ecommerce.api.model.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Suppliers")
public class Supplier {
    @Id
    @Column(name = "SupplierID")
    private Integer supplierId;

    @Column(name = "SupplierName", length = 100)
    private String supplierName;

    @Column(name = "ContactEmail", length = 100)
    private String contactEmail;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSupplier> productSuppliers = new ArrayList<>();

    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public List<ProductSupplier> getProductSuppliers() { return productSuppliers; }
    public void setProductSuppliers(List<ProductSupplier> productSuppliers) { this.productSuppliers = productSuppliers; }
}
