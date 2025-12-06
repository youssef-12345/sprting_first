package com.example.supplychain.sale.service;

import com.example.supplychain.sale.dto.SaleDTO;
import com.example.supplychain.sale.entity.Sale;
import com.example.supplychain.sale.mapper.SaleMapper;
import com.example.supplychain.sale.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleMapper saleMapper;

    public SaleDTO createSale(SaleDTO saleDTO) {
        Sale sale = saleMapper.toEntity(saleDTO);
        if (sale.getStatus() == null) {
            sale.setStatus(Sale.SaleStatus.PENDING);
        }
        Sale savedSale = saleRepository.save(sale);
        return saleMapper.toDTO(savedSale);
    }

    public SaleDTO getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + id));
        return saleMapper.toDTO(sale);
    }

    public SaleDTO getSaleByOrderNumber(String orderNumber) {
        Sale sale = saleRepository.findBySaleOrderNumber(orderNumber)
                .orElseThrow(() -> new RuntimeException("Sale not found with order number: " + orderNumber));
        return saleMapper.toDTO(sale);
    }

    public List<SaleDTO> getAllSales() {
        return saleRepository.findAll().stream()
                .map(saleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<SaleDTO> getSalesByStatus(String status) {
        return saleRepository.findByStatus(status).stream()
                .map(saleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<SaleDTO> getSalesByProduct(Long productId) {
        return saleRepository.findByProductId(productId).stream()
                .map(saleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SaleDTO updateSale(Long id, SaleDTO saleDTO) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + id));

        sale.setQuantity(saleDTO.getQuantity());
        sale.setUnitPrice(saleDTO.getUnitPrice());
        sale.setTotalAmount(saleDTO.getTotalAmount());
        sale.setStatus(Sale.SaleStatus.valueOf(saleDTO.getStatus()));
        sale.setCustomerName(saleDTO.getCustomerName());
        sale.setDeliveryAddress(saleDTO.getDeliveryAddress());

        Sale updatedSale = saleRepository.save(sale);
        return saleMapper.toDTO(updatedSale);
    }

    public void deleteSale(Long id) {
        if (!saleRepository.existsById(id)) {
            throw new RuntimeException("Sale not found with id: " + id);
        }
        saleRepository.deleteById(id);
    }
}
