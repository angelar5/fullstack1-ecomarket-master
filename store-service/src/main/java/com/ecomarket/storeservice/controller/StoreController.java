package com.ecomarket.storeservice.controller;
import com.ecomarket.storeservice.entity.Store;
import com.ecomarket.storeservice.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/stores") @RequiredArgsConstructor
public class StoreController {
    private final StoreRepository repository;
    @GetMapping public ResponseEntity<List<Store>> getAll() { return ResponseEntity.ok(repository.findAll()); }
    @PostMapping public ResponseEntity<Store> create(@RequestBody Store s) { return ResponseEntity.ok(repository.save(s)); }
}