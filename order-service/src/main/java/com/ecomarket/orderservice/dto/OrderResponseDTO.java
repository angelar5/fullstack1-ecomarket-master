package com.ecomarket.orderservice.dto;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderResponseDTO {
    private Long id;
    private String customerName;
    private String customerEmail;
    private String shippingAddress;
    private String status;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
}