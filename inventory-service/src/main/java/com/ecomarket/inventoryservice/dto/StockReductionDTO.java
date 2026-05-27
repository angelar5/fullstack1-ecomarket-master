package com.ecomarket.inventoryservice.dto;
import jakarta.validation.constraints.*;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StockReductionDTO {
    @NotNull private Long productId;
    @NotNull @Positive private Integer quantity;
}