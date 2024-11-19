package com.epam.inventorystore.service.impl;

import com.epam.inventorystore.dto.request.ProductAddReqDto;
import com.epam.inventorystore.dto.request.ProductUpdateReqDto;
import com.epam.inventorystore.dto.response.ProductResDto;
import com.epam.inventorystore.dto.response.ProductUpdateResDto;
import com.epam.inventorystore.entity.Product;
import com.epam.inventorystore.mapper.ProductMapper;
import com.epam.inventorystore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_WhenProductDoesNotExist_ShouldCreateNewProduct() {
        ProductAddReqDto addReqDto = new ProductAddReqDto("Product1", "Description", 100.0, 10);
        Product newProduct = new Product(null, "Product1", "Description", 100.0, 10);
        Product savedProduct = new Product(1L, "Product1", "Description", 100.0, 10);

        when(productRepository.findByName("Product1")).thenReturn(Optional.empty());
        when(productMapper.toEntity(addReqDto)).thenReturn(newProduct);
        when(productRepository.save(newProduct)).thenReturn(savedProduct);

        Long resultId = productService.create(addReqDto);

        assertEquals(1L, resultId);
        verify(productRepository).save(newProduct);
    }

    @Test
    void testGetAll_ShouldReturnProductList() {
        Product product1 = new Product(1L, "Product1", "Description1", 100.0, 10);
        Product product2 = new Product(2L, "Product2", "Description2", 200.0, 20);
        ProductResDto resDto1 = new ProductResDto(1L, "Product1", "Description1", 100.0, 10);
        ProductResDto resDto2 = new ProductResDto(2L, "Product2", "Description2", 200.0, 20);

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));
        when(productMapper.toResponseDto(product1)).thenReturn(resDto1);
        when(productMapper.toResponseDto(product2)).thenReturn(resDto2);

        List<ProductResDto> result = productService.getAll();

        assertEquals(2, result.size());
        assertEquals(resDto1, result.get(0));
        assertEquals(resDto2, result.get(1));
    }


    @Test
    void testDeleteById_WhenProductDoesNotExist_ShouldThrowException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> productService.deleteById(1L));
        assertEquals("Product with id: 1 not exists. Cannot delete", exception.getMessage());
    }

    @Test
    void testUpdate_WhenProductExists_ShouldUpdateAndReturnUpdatedDto() {
        ProductUpdateReqDto updateReqDto = new ProductUpdateReqDto("Updated Product", "Updated Description", 150.0, 15);
        Product existingProduct = new Product(1L, "Product1", "Description", 100.0, 10);
        Product updatedProduct = new Product(1L, "Updated Product", "Updated Description", 150.0, 15);
        ProductUpdateResDto updateResDto = new ProductUpdateResDto(1L, "Updated Product", "Updated Description", 150.0, 15);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
        when(productMapper.toProductUpdateResDto(updatedProduct)).thenReturn(updateResDto);

        ProductUpdateResDto result = productService.update(1L, updateReqDto);

        assertEquals(updateResDto, result);
        verify(productRepository).save(existingProduct);
    }

    @Test
    void testUpdate_WhenProductDoesNotExist_ShouldThrowException() {
        ProductUpdateReqDto updateReqDto = new ProductUpdateReqDto("Updated Product", "Updated Description", 150.0, 15);

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> productService.update(1L, updateReqDto));
        assertEquals("cannot update product. error: product with id: 1 not found", exception.getMessage());
    }

    @Test
    void testGetById_WhenProductExists_ShouldReturnProductDto() {
        Product product = new Product(1L, "Product1", "Description", 100.0, 10);
        ProductResDto resDto = new ProductResDto(1L, "Product1", "Description", 100.0, 10);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toResponseDto(product)).thenReturn(resDto);

        ProductResDto result = productService.getById(1L);

        assertEquals(resDto, result);
    }

    @Test
    void testGetById_WhenProductDoesNotExist_ShouldThrowException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> productService.getById(1L));
        assertEquals("Product with id: 1 not found", exception.getMessage());
    }
}