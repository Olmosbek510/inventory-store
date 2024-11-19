package com.epam.inventorystore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateReqDto {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    public ProductUpdateReqDto(String newDescription, double price, int quantity) {
        this.description = newDescription;
        this.quantity = quantity;
        this.price = price;
    }
}
