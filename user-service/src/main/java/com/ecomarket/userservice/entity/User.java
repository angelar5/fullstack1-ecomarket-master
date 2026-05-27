package com.ecomarket.userservice.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "users") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100) private String name;
    @Column(nullable = false, unique = true, length = 100) private String email;
    @Column(name = "role_name", nullable = false, length = 50) private String roleName;
}