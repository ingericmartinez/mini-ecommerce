package com.ecommerce.api.model.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductDtos {
    public static class ProductInput {
        @NotBlank
        public String name;
        @NotNull @DecimalMin(value = "0.0", inclusive = true)
        public BigDecimal price;
        @NotNull
        public Integer categoryId;
    }
    public static class ProductResponse {
        public Integer productId;
        public String name;
        public BigDecimal price;
        public Integer categoryId;
    }
}
