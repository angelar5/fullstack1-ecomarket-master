package com.ecomarket.reviewservice.controller;
import com.ecomarket.reviewservice.entity.Review;
import com.ecomarket.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/reviews") @RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository repository;
    @GetMapping public ResponseEntity<List<Review>> getAll() { return ResponseEntity.ok(repository.findAll()); }
    @GetMapping("/product/{productId}") public ResponseEntity<List<Review>> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(repository.findByProductId(productId));
    }
    @PostMapping public ResponseEntity<Review> create(@RequestBody Review r) { return ResponseEntity.ok(repository.save(r)); }
}