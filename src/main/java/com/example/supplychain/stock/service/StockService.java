package com.example.supplychain.stock.service;

import com.example.supplychain.stock.dto.StockDTO;
import com.example.supplychain.stock.entity.Stock;
import com.example.supplychain.stock.mapper.StockMapper;
import com.example.supplychain.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    public StockDTO createStock(StockDTO stockDTO) {
        Stock stock = stockMapper.toEntity(stockDTO);
        Stock savedStock = stockRepository.save(stock);
        return stockMapper.toDTO(savedStock);
    }

    public StockDTO getStockById(Long id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));
        return stockMapper.toDTO(stock);
    }

    public StockDTO getStockByProductId(Long productId) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Stock not found for product: " + productId));
        return stockMapper.toDTO(stock);
    }

    public List<StockDTO> getAllStocks() {
        return stockRepository.findAll().stream()
                .map(stockMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<StockDTO> getLowStockItems() {
        return stockRepository.findByQuantityLessThan(10).stream()
                .map(stockMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StockDTO updateStock(Long id, StockDTO stockDTO) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));

        stock.setQuantity(stockDTO.getQuantity());
        stock.setMinimumLevel(stockDTO.getMinimumLevel());
        stock.setMaximumLevel(stockDTO.getMaximumLevel());
        stock.setWarehouseLocation(stockDTO.getWarehouseLocation());

        Stock updatedStock = stockRepository.save(stock);
        return stockMapper.toDTO(updatedStock);
    }

    public void deleteStock(Long id) {
        if (!stockRepository.existsById(id)) {
            throw new RuntimeException("Stock not found with id: " + id);
        }
        stockRepository.deleteById(id);
    }
}
