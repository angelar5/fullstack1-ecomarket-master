package com.ecomarket.inventoryservice.controller;
import com.ecomarket.inventoryservice.dto.ProductDTO;
import com.ecomarket.inventoryservice.dto.StockReductionDTO;
import com.ecomarket.inventoryservice.entity.Product;
import com.ecomarket.inventoryservice.exception.ResourceNotFoundException;
import com.ecomarket.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController @RequestMapping("/api/products") @RequiredArgsConstructor @Slf4j
public class ProductController {
    private final ProductRepository productRepository;
    @GetMapping public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(productRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList()));
    }
    @GetMapping("/{id}") public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.findById(id).map(this::toDTO).orElseThrow(() -> new ResourceNotFoundException("No existe")));
    }
    @PutMapping("/reduce-stock") @Transactional
    public ResponseEntity<Void> reduceStock(@RequestBody List<StockReductionDTO> reductions) {
        log.info("Reduciendo stock de {} productos", reductions.size());
        for (StockReductionDTO reduction : reductions) {
            Product p = productRepository.findById(reduction.getProductId()).orElseThrow(() -> new ResourceNotFoundException("No existe"));
            if (p.getStock() < reduction.getQuantity()) {
                throw new IllegalArgumentException("Stock insuficiente para: " + p.getName());
            }
            p.setStock(p.getStock() - reduction.getQuantity());
            productRepository.save(p);
        }
        return ResponseEntity.ok().build();
    }
    private ProductDTO toDTO(Product p) {
        return ProductDTO.builder().id(p.getId()).name(p.getName()).description(p.getDescription()).price(p.getPrice()).stock(p.getStock())
            .categoryId(p.getCategory().getId()).categoryName(p.getCategory().getName()).build();
    }
}