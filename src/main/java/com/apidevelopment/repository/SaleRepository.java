package com.apidevelopment.repository;

import com.apidevelopment.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findByProductName(String productName);
}
