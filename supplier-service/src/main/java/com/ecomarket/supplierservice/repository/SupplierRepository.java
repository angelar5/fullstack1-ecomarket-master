package com.ecomarket.supplierservice.repository;
import com.ecomarket.supplierservice.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {}