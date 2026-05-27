package com.ecomarket.supplierservice.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "suppliers") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Supplier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_name") private String companyName;
    @Column(name = "contact_email") private String contactEmail;
    @Column(name = "category_supply") private String categorySupply;
}