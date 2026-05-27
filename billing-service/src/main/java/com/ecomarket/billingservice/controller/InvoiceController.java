package com.ecomarket.billingservice.controller;
import com.ecomarket.billingservice.entity.Invoice;
import com.ecomarket.billingservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
@RestController @RequestMapping("/api/invoices") @RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceRepository repository;
    @GetMapping public ResponseEntity<List<Invoice>> getAll() { return ResponseEntity.ok(repository.findAll()); }
    @PostMapping public ResponseEntity<Invoice> create(@RequestBody Map<String, Object> req) {
        Long orderId = Long.valueOf(req.get("orderId").toString());
        BigDecimal total = new BigDecimal(req.get("totalAmount").toString());
        BigDecimal tax = total.multiply(BigDecimal.valueOf(0.19)); // 19% IVA en Chile
        
        Invoice invoice = Invoice.builder()
            .orderId(orderId)
            .invoiceNumber("FAC-" + System.currentTimeMillis() + "-" + new Random().nextInt(100))
            .taxAmount(tax)
            .totalAmount(total)
            .issuedDate(LocalDateTime.now())
            .build();
            
        return ResponseEntity.ok(repository.save(invoice));
    }
}