package com.example.supplychain.stock.mapper;

import com.example.supplychain.stock.dto.StockDTO;
import com.example.supplychain.stock.entity.Stock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    @Autowired
    private ModelMapper modelMapper;

    public StockDTO toDTO(Stock entity) {
        return modelMapper.map(entity, StockDTO.class);
    }

    public Stock toEntity(StockDTO dto) {
        return modelMapper.map(dto, Stock.class);
    }
}
