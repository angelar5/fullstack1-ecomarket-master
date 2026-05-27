package com.ecomarket.supplierservice.controller;
import com.ecomarket.supplierservice.entity.Supplier;
import com.ecomarket.supplierservice.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/suppliers") @RequiredArgsConstructor
public class SupplierController {
    private final SupplierRepository repository;
    @GetMapping public ResponseEntity<List<Supplier>> getAll() { return ResponseEntity.ok(repository.findAll()); }
    @PostMapping public ResponseEntity<Supplier> create(@RequestBody Supplier s) { return ResponseEntity.ok(repository.save(s)); }
}