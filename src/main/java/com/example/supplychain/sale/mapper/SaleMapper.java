package com.example.supplychain.sale.mapper;

import com.example.supplychain.sale.dto.SaleDTO;
import com.example.supplychain.sale.entity.Sale;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SaleDTO toDTO(Sale entity) {
        return modelMapper.map(entity, SaleDTO.class);
    }

    public Sale toEntity(SaleDTO dto) {
        return modelMapper.map(dto, Sale.class);
    }
}
