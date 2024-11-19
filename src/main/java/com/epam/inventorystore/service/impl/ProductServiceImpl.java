package com.epam.inventorystore.service.impl;

import com.epam.inventorystore.dto.request.ProductAddReqDto;
import com.epam.inventorystore.dto.request.ProductUpdateReqDto;
import com.epam.inventorystore.dto.response.ProductResDto;
import com.epam.inventorystore.dto.response.ProductUpdateResDto;
import com.epam.inventorystore.entity.Product;
import com.epam.inventorystore.mapper.ProductMapper;
import com.epam.inventorystore.repository.ProductRepository;
import com.epam.inventorystore.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public Long create(ProductAddReqDto addReqDto) {
        Product product = productMapper.toEntity(addReqDto);
        if (productRepository.existsProductByName(product.getName()) && productRepository.findByName(product.getName()).isPresent()) {
            var existingProduct = productRepository.findByName(product.getName()).get();
            if (!product.getDescription().isBlank()) {
                existingProduct.setDescription(product.getDescription());
            }
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
            productRepository.save(existingProduct);
            return existingProduct.getId();
        }
        Product save = productRepository.save(product);
        return save.getId();
    }

    @Override
    public List<ProductResDto> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoSuchElementException(String.format("Product with id: %d not exists. Cannot delete", id));
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductUpdateResDto update(Long id, ProductUpdateReqDto updateReqDto) {
        if (productRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException(String.format("cannot update product. error: product with id: %d not found", id));
        }
        Product product = productRepository.findById(id).get();
        if (!updateReqDto.getDescription().isBlank()) {
            product.setDescription(updateReqDto.getDescription());
        }
        if (!updateReqDto.getName().isBlank()) {
            product.setName(updateReqDto.getName());
        }
        if (updateReqDto.getQuantity() != null) {
            product.setQuantity(updateReqDto.getQuantity());
        }
        if (updateReqDto.getPrice() != null) {
            product.setPrice(updateReqDto.getPrice());
        }
        System.out.println(product);
        Product save = productRepository.save(product);
        return productMapper.toProductUpdateResDto(save);
    }

    @Override
    public ProductResDto getById(long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productMapper.toResponseDto(productOptional.get());
        }
        throw new NoSuchElementException(String.format("Product with id: %d not found", id));
    }
}
