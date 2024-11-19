package com.epam.inventorystore.controller;

import com.epam.inventorystore.dto.request.ProductAddReqDto;
import com.epam.inventorystore.dto.request.ProductUpdateReqDto;
import com.epam.inventorystore.dto.response.ProductResDto;
import com.epam.inventorystore.dto.response.ProductUpdateResDto;
import com.epam.inventorystore.service.ProductService;
import com.epam.inventorystore.uri.ProductURI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProductURI.BASE_URI)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid ProductAddReqDto addReqDto) {
        Long productId = productService.create(addReqDto);
        return ResponseEntity.ok(productId);
    }

    @GetMapping
    public ResponseEntity<List<ProductResDto>> getAll() {
        List<ProductResDto> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping(ProductURI.PRODUCT)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok(String.format("Product with id: %d deleted successfully", id));
    }

    @PutMapping(ProductURI.PRODUCT)
    public ResponseEntity<ProductUpdateResDto> update(@PathVariable Long id, @RequestBody ProductUpdateReqDto updateReqDto) {
        ProductUpdateResDto productUpdateResDto = productService.update(id, updateReqDto);
        return ResponseEntity.ok(productUpdateResDto);
    }

    @GetMapping(ProductURI.PRODUCT)
    public ResponseEntity<ProductResDto> getById(@PathVariable Long id) {
        ProductResDto product = productService.getById(id);
        return ResponseEntity.ok(product);
    }
}
