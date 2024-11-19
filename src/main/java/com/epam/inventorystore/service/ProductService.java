package com.epam.inventorystore.service;

import com.epam.inventorystore.dto.request.ProductAddReqDto;
import com.epam.inventorystore.dto.request.ProductUpdateReqDto;
import com.epam.inventorystore.dto.response.ProductResDto;
import com.epam.inventorystore.dto.response.ProductUpdateResDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    Long create(@Valid ProductAddReqDto addReqDto);

    List<ProductResDto> getAll();

    void deleteById(Long id);

    ProductUpdateResDto update(Long id, ProductUpdateReqDto updateReqDto);

    ProductResDto getById(long id);
}
