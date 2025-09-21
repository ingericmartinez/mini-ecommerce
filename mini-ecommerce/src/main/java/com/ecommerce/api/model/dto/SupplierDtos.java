package com.ecommerce.api.model.dto;

import jakarta.validation.constraints.*;

public class SupplierDtos {
    public static class SupplierInput {
        @NotBlank
        public String supplierName;
        @NotBlank @Email @Size(max = 100)
        public String contactEmail;
    }
    public static class SupplierResponse {
        public Integer supplierId;
        public String supplierName;
        public String contactEmail;
    }
}
