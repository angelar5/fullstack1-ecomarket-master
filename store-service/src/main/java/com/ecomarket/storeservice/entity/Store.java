package com.ecomarket.storeservice.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "stores") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    @Column(name = "opening_hours") private String openingHours;
    @Column(name = "manager_email") private String managerEmail;
}