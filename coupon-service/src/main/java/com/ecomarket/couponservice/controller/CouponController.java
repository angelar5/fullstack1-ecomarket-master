package com.ecomarket.couponservice.controller;
import com.ecomarket.couponservice.entity.Coupon;
import com.ecomarket.couponservice.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/coupons") @RequiredArgsConstructor @Slf4j
public class CouponController {
    private final CouponRepository repository;
    @GetMapping("/validate/{code}")
    public ResponseEntity<Double> validateCoupon(@PathVariable String code) {
        log.info("Validando cupÃ³n: {}", code);
        return repository.findByCode(code)
                .filter(Coupon::getActive)
                .map(c -> ResponseEntity.ok(c.getDiscountPercent()))
                .orElse(ResponseEntity.ok(0.0));
    }
}