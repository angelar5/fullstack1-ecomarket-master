package com.ecomarket.orderservice.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
@Entity @Table(name = "orders") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_name") private String customerName;
    @Column(name = "customer_email") private String customerEmail;
    @Column(name = "shipping_address") private String shippingAddress;
    private String status;
    @Column(name = "order_date") private LocalDateTime orderDate;
    @Column(name = "total_amount") private BigDecimal totalAmount;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default private List<OrderItem> items = new ArrayList<>();
}