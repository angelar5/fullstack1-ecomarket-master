package com.ecomarket.orderservice.dto;
import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StockReductionDTO {
    private Long productId;
    private Integer quantity;
}