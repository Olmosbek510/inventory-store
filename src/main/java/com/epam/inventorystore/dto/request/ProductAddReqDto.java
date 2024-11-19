package com.epam.inventorystore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddReqDto {
    @NotBlank(message = "name cannot be blank")
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
