package com.epam.inventorystore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateResDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
