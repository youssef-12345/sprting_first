package com.example.supplychain.analytics.dto;

import java.math.BigDecimal;

public class SalesAnalyticsDTO {

    private Long totalOrders;
    private Integer totalQuantitySold;
    private BigDecimal totalRevenue;
    private String topProductId;
    private String periodLabel;

    public SalesAnalyticsDTO() {}

    public SalesAnalyticsDTO(Long totalOrders, Integer totalQuantitySold, BigDecimal totalRevenue) {
        this.totalOrders = totalOrders;
        this.totalQuantitySold = totalQuantitySold;
        this.totalRevenue = totalRevenue;
    }

    public Long getTotalOrders() { return totalOrders; }
    public void setTotalOrders(Long totalOrders) { this.totalOrders = totalOrders; }

    public Integer getTotalQuantitySold() { return totalQuantitySold; }
    public void setTotalQuantitySold(Integer totalQuantitySold) { this.totalQuantitySold = totalQuantitySold; }

    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }

    public String getTopProductId() { return topProductId; }
    public void setTopProductId(String topProductId) { this.topProductId = topProductId; }

    public String getPeriodLabel() { return periodLabel; }
    public void setPeriodLabel(String periodLabel) { this.periodLabel = periodLabel; }
}
