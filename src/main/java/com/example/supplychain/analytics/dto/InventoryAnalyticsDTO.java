package com.example.supplychain.analytics.dto;

import java.math.BigDecimal;

public class InventoryAnalyticsDTO {

    private Long totalProducts;
    private Integer totalStockQuantity;
    private Integer lowStockCount;
    private BigDecimal totalInventoryValue;

    public InventoryAnalyticsDTO() {}

    public InventoryAnalyticsDTO(Long totalProducts, Integer totalStockQuantity, Integer lowStockCount) {
        this.totalProducts = totalProducts;
        this.totalStockQuantity = totalStockQuantity;
        this.lowStockCount = lowStockCount;
    }

    public Long getTotalProducts() { return totalProducts; }
    public void setTotalProducts(Long totalProducts) { this.totalProducts = totalProducts; }

    public Integer getTotalStockQuantity() { return totalStockQuantity; }
    public void setTotalStockQuantity(Integer totalStockQuantity) { this.totalStockQuantity = totalStockQuantity; }

    public Integer getLowStockCount() { return lowStockCount; }
    public void setLowStockCount(Integer lowStockCount) { this.lowStockCount = lowStockCount; }

    public BigDecimal getTotalInventoryValue() { return totalInventoryValue; }
    public void setTotalInventoryValue(BigDecimal totalInventoryValue) { this.totalInventoryValue = totalInventoryValue; }
}
