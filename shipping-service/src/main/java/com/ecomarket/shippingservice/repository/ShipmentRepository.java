package com.ecomarket.shippingservice.repository;
import com.ecomarket.shippingservice.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {}