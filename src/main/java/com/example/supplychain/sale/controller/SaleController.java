package com.example.supplychain.sale.controller;

import com.example.supplychain.sale.dto.SaleDTO;
import com.example.supplychain.sale.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@Tag(name = "Sales", description = "Sales management endpoints")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @Operation(summary = "Create new sale", description = "Create a new sales order")
    public ResponseEntity<SaleDTO> createSale(@Valid @RequestBody SaleDTO saleDTO) {
        SaleDTO createdSale = saleService.createSale(saleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    @Operation(summary = "Get sale by ID", description = "Retrieve a sales order by its ID")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id) {
        SaleDTO sale = saleService.getSaleById(id);
        return ResponseEntity.ok(sale);
    }

    @GetMapping("/order/{orderNumber}")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    @Operation(summary = "Get sale by order number", description = "Retrieve a sales order by its order number")
    public ResponseEntity<SaleDTO> getSaleByOrderNumber(@PathVariable String orderNumber) {
        SaleDTO sale = saleService.getSaleByOrderNumber(orderNumber);
        return ResponseEntity.ok(sale);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    @Operation(summary = "Get all sales", description = "Retrieve all sales orders")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        List<SaleDTO> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    @Operation(summary = "Get sales by status", description = "Retrieve all sales with a specific status")
    public ResponseEntity<List<SaleDTO>> getSalesByStatus(@PathVariable String status) {
        List<SaleDTO> sales = saleService.getSalesByStatus(status);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    @Operation(summary = "Get sales by product", description = "Retrieve all sales for a specific product")
    public ResponseEntity<List<SaleDTO>> getSalesByProduct(@PathVariable Long productId) {
        List<SaleDTO> sales = saleService.getSalesByProduct(productId);
        return ResponseEntity.ok(sales);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @Operation(summary = "Update sale", description = "Update an existing sales order")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @Valid @RequestBody SaleDTO saleDTO) {
        SaleDTO updatedSale = saleService.updateSale(id, saleDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete sale", description = "Delete a sales order")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
