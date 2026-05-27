package com.ecomarket.authservice.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "role_permissions") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RolePermission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name", nullable = false, length = 50) private String roleName;
    @Column(name = "permission_key", nullable = false, length = 50) private String permissionKey;
}