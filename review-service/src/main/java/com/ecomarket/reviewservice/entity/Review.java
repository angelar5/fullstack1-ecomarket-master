package com.ecomarket.reviewservice.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "reviews") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id") private Long productId;
    @Column(name = "customer_name") private String customerName;
    private Integer rating;
    private String comment;
}