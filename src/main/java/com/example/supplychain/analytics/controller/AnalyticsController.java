package com.example.supplychain.analytics.controller;

import com.example.supplychain.analytics.dto.InventoryAnalyticsDTO;
import com.example.supplychain.analytics.dto.SalesAnalyticsDTO;
import com.example.supplychain.analytics.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
@Tag(name = "Analytics", description = "Analytics and reporting endpoints")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/sales")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @Operation(summary = "Get sales analytics", description = "Retrieve sales analytics data")
    public ResponseEntity<SalesAnalyticsDTO> getSalesAnalytics() {
        SalesAnalyticsDTO analytics = analyticsService.getSalesAnalytics();
        return ResponseEntity.ok(analytics);
    }

    @GetMapping("/inventory")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @Operation(summary = "Get inventory analytics", description = "Retrieve inventory analytics data")
    public ResponseEntity<InventoryAnalyticsDTO> getInventoryAnalytics() {
        InventoryAnalyticsDTO analytics = analyticsService.getInventoryAnalytics();
        return ResponseEntity.ok(analytics);
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @Operation(summary = "Get dashboard summary", description = "Retrieve overall dashboard summary")
    public ResponseEntity<Map<String, Object>> getDashboardSummary() {
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("totalProducts", analyticsService.getTotalProductCount());
        dashboard.put("totalSuppliers", analyticsService.getTotalSupplierCount());
        dashboard.put("totalSales", analyticsService.getTotalSalesCount());
        dashboard.put("salesAnalytics", analyticsService.getSalesAnalytics());
        dashboard.put("inventoryAnalytics", analyticsService.getInventoryAnalytics());
        return ResponseEntity.ok(dashboard);
    }
}
