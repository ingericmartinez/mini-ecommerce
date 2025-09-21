package com.ecommerce.api.model.dto;

import jakarta.validation.constraints.*;

public class CategoryDtos {
    public static class CategoryInput {
        @NotBlank
        public String categoryName;
    }
    public static class CategoryResponse {
        public Integer categoryId;
        public String categoryName;
    }
}
