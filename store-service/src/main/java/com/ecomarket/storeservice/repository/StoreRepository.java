package com.ecomarket.storeservice.repository;
import com.ecomarket.storeservice.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {}