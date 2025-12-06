package com.example.supplychain.product.repository;

import com.example.supplychain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductCode(String productCode);
    List<Product> findByCategory(String category);
    List<Product> findByIsActive(Boolean isActive);
}
