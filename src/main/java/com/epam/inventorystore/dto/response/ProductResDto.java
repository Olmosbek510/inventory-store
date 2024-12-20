package com.epam.inventorystore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Integer quantity;
}
