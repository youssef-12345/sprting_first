package com.example.supplychain.analytics.service;

import com.example.supplychain.analytics.dto.InventoryAnalyticsDTO;
import com.example.supplychain.analytics.dto.SalesAnalyticsDTO;
import com.example.supplychain.product.repository.ProductRepository;
import com.example.supplychain.sale.repository.SaleRepository;
import com.example.supplychain.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AnalyticsService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    public SalesAnalyticsDTO getSalesAnalytics() {
        long totalOrders = saleRepository.count();
        Integer totalQuantitySold = saleRepository.findAll().stream()
                .mapToInt(sale -> sale.getQuantity())
                .sum();
        BigDecimal totalRevenue = saleRepository.findAll().stream()
                .map(sale -> sale.getTotalAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        SalesAnalyticsDTO analytics = new SalesAnalyticsDTO(totalOrders, totalQuantitySold, totalRevenue);
        analytics.setPeriodLabel("All Time");
        return analytics;
    }

    public InventoryAnalyticsDTO getInventoryAnalytics() {
        long totalProducts = productRepository.count();
        Integer totalStockQuantity = stockRepository.findAll().stream()
                .mapToInt(stock -> stock.getQuantity())
                .sum();
        Integer lowStockCount = (int) stockRepository.findByQuantityLessThan(10).size();
        BigDecimal totalInventoryValue = productRepository.findAll().stream()
                .mapToDouble(product -> product.getUnitPrice().doubleValue())
                .sum() > 0 ? BigDecimal.valueOf(stockRepository.findAll().stream()
                .mapToDouble(stock -> stock.getQuantity())
                .sum()) : BigDecimal.ZERO;

        InventoryAnalyticsDTO analytics = new InventoryAnalyticsDTO(totalProducts, totalStockQuantity, lowStockCount);
        analytics.setTotalInventoryValue(totalInventoryValue);
        return analytics;
    }

    public long getTotalProductCount() {
        return productRepository.count();
    }

    public long getTotalSupplierCount() {
        // Implementation would use SupplierRepository
        return 0;
    }

    public long getTotalSalesCount() {
        return saleRepository.count();
    }
}
