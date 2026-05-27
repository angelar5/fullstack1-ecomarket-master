package com.ecomarket.inventoryservice.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity @Table(name = "categories") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 100) private String name;
    @Column(length = 255) private String description;
}