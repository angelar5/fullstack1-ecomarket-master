package com.ecomarket.shippingservice.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "shipments") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Shipment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id") private Long orderId;
    @Column(name = "tracking_code") private String trackingCode;
    private String status;
    @Column(name = "delivery_address") private String deliveryAddress;
}