package com.example.supplychain.supplier.mapper;

import com.example.supplychain.supplier.dto.SupplierDTO;
import com.example.supplychain.supplier.entity.Supplier;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SupplierDTO toDTO(Supplier entity) {
        return modelMapper.map(entity, SupplierDTO.class);
    }

    public Supplier toEntity(SupplierDTO dto) {
        return modelMapper.map(dto, Supplier.class);
    }
}
