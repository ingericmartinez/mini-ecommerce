package com.ecommerce.api.model.dto;

import jakarta.validation.constraints.*;

public class CustomerDtos {
    public static class CustomerInput {
        @NotBlank
        public String name;
        @NotBlank @Email @Size(max = 100)
        public String email;
    }
    public static class CustomerResponse {
        public Integer customerId;
        public String name;
        public String email;
    }
}
