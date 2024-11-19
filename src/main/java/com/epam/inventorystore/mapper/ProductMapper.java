package com.epam.inventorystore.mapper;

import com.epam.inventorystore.dto.request.ProductAddReqDto;
import com.epam.inventorystore.dto.request.ProductUpdateReqDto;
import com.epam.inventorystore.dto.response.ProductResDto;
import com.epam.inventorystore.dto.response.ProductUpdateResDto;
import com.epam.inventorystore.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "description", target = "description")
    Product toEntity(ProductAddReqDto addReqDto);

    ProductResDto toResponseDto(Product product);

    Product toEntity(ProductUpdateReqDto updateRequestDto);

    ProductUpdateResDto toProductUpdateResDto(Product product);
}
