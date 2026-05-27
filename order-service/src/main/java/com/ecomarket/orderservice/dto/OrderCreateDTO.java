package com.ecomarket.orderservice.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderCreateDTO {
    @NotBlank private String customerName;
    @NotBlank @Email private String customerEmail;
    @NotBlank private String shippingAddress;
    private String couponCode;
    @NotEmpty private List<OrderItemCreateDTO> items;
}