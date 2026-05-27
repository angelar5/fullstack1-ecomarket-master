package com.ecomarket.billingservice.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name = "invoices") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id") private Long orderId;
    @Column(name = "invoice_number") private String invoiceNumber;
    @Column(name = "tax_amount") private BigDecimal taxAmount;
    @Column(name = "total_amount") private BigDecimal totalAmount;
    @Column(name = "issued_date") private LocalDateTime issuedDate;
}