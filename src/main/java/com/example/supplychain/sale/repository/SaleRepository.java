package com.example.supplychain.sale.repository;

import com.example.supplychain.sale.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    Optional<Sale> findBySaleOrderNumber(String saleOrderNumber);
    List<Sale> findByStatus(String status);
    List<Sale> findByProductId(Long productId);
}
