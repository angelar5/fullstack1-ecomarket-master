package com.ecomarket.shippingservice.controller;
import com.ecomarket.shippingservice.entity.Shipment;
import com.ecomarket.shippingservice.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/shipments") @RequiredArgsConstructor
public class ShipmentController {
    private final ShipmentRepository repository;
    @GetMapping public ResponseEntity<List<Shipment>> getAll() { return ResponseEntity.ok(repository.findAll()); }
    @PostMapping public ResponseEntity<Shipment> create(@RequestBody Shipment s) {
        s.setTrackingCode("TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        s.setStatus("PENDING");
        return ResponseEntity.ok(repository.save(s));
    }
}