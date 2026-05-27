package com.ecomarket.orderservice.controller;
import com.ecomarket.orderservice.dto.*;
import com.ecomarket.orderservice.entity.*;
import com.ecomarket.orderservice.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@RestController @RequestMapping("/api/orders") @RequiredArgsConstructor @Slf4j
public class OrderController {
    private final OrderRepository orderRepository;
    private final WebClient inventoryWebClient;
    private final WebClient couponWebClient;
    private final WebClient billingWebClient;

    @GetMapping public ResponseEntity<List<Order>> getAll() { return ResponseEntity.ok(orderRepository.findAll()); }

    @PostMapping @Transactional
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderCreateDTO dto) {
        log.info("Creando orden para {}", dto.getCustomerName());
        List<StockReductionDTO> reductions = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> items = new ArrayList<>();

        for (OrderItemCreateDTO itemDto : dto.getItems()) {
            ProductDTO p = inventoryWebClient.get()
                .uri("/api/products/{id}", itemDto.getProductId())
                .retrieve().bodyToMono(ProductDTO.class).timeout(Duration.ofSeconds(5)).block();

            if (p == null || p.getStock() < itemDto.getQuantity()) {
                throw new IllegalArgumentException("Stock insuficiente para producto ID: " + itemDto.getProductId());
            }

            BigDecimal linePrice = p.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity()));
            total = total.add(linePrice);

            items.add(OrderItem.builder().productId(p.getId()).quantity(itemDto.getQuantity()).unitPrice(p.getPrice()).build());
            reductions.add(new StockReductionDTO(p.getId(), itemDto.getQuantity()));
        }

        // Aplicar cupÃ³n si existe llamando a coupon-service
        if (dto.getCouponCode() != null && !dto.getCouponCode().isBlank()) {
            try {
                Double pct = couponWebClient.get()
                    .uri("/api/coupons/validate/{code}", dto.getCouponCode())
                    .retrieve().bodyToMono(Double.class).timeout(Duration.ofSeconds(5)).block();
                if (pct != null && pct > 0) {
                    BigDecimal discount = total.multiply(BigDecimal.valueOf(pct / 100.0));
                    total = total.subtract(discount);
                    log.info("CupÃ³n {} aplicado. Descuento del {}%.", dto.getCouponCode(), pct);
                }
            } catch (Exception ex) {
                log.warn("Error al validar cupÃ³n: {}", ex.getMessage());
            }
        }

        // Reducir stock en inventory-service
        inventoryWebClient.put().uri("/api/products/reduce-stock").bodyValue(reductions)
            .retrieve().toBodilessEntity().timeout(Duration.ofSeconds(5)).block();

        Order order = Order.builder()
            .customerName(dto.getCustomerName())
            .customerEmail(dto.getCustomerEmail())
            .shippingAddress(dto.getShippingAddress())
            .status("CONFIRMED")
            .orderDate(LocalDateTime.now())
            .totalAmount(total)
            .build();

        for (OrderItem i : items) { i.setOrder(order); }
        order.setItems(items);

        Order saved = orderRepository.save(order);

        // Emitir factura en billing-service
        try {
            Map<String, Object> invoiceReq = new HashMap<>();
            invoiceReq.put("orderId", saved.getId());
            invoiceReq.put("totalAmount", saved.getTotalAmount());
            billingWebClient.post().uri("/api/invoices").bodyValue(invoiceReq)
                .retrieve().toBodilessEntity().timeout(Duration.ofSeconds(5)).block();
            log.info("Factura generada en billing-service para orden ID {}", saved.getId());
        } catch (Exception ex) {
            log.warn("Error al emitir factura: {}", ex.getMessage());
        }

        return ResponseEntity.status(201).body(OrderResponseDTO.builder().id(saved.getId()).customerName(saved.getCustomerName())
            .customerEmail(saved.getCustomerEmail()).shippingAddress(saved.getShippingAddress()).status(saved.getStatus())
            .orderDate(saved.getOrderDate()).totalAmount(saved.getTotalAmount()).build());
    }
}