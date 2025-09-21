package com.ecommerce.api.model.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

public class OrderDtos {
    public static class OrderItemInput {
        @NotNull
        public Integer productId;
        @NotNull @Min(1)
        public Integer quantity;
    }
    public static class OrderInput {
        @NotNull
        public Integer customerId;
        @NotNull
        public LocalDate orderDate;
        @NotEmpty
        public List<OrderItemInput> items;
    }
    public static class OrderResponse {
        public Integer orderId;
        public Integer customerId;
        public LocalDate orderDate;
    }
}
