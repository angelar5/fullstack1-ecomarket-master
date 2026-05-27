package com.ecomarket.orderservice.dto;
import jakarta.validation.constraints.*;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItemCreateDTO {
    @NotNull private Long productId;
    @NotNull @Positive private Integer quantity;
}