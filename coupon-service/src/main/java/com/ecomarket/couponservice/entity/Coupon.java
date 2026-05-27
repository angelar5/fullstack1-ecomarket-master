package com.ecomarket.couponservice.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "coupons") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Coupon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @Column(name = "discount_percent") private Double discountPercent;
    private Boolean active;
}