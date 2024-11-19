package com.epam.inventorystore.repository;

import com.epam.inventorystore.dto.response.ProductUpdateResDto;
import com.epam.inventorystore.entity.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsProductByName(@NotBlank(message = "name cannot be blank") String name);

    Optional<Product> findByName(String name);

    @Modifying
    @Query("UPDATE Product p SET " +
            "p.name = CASE WHEN :#{#updatedProduct.name} IS NOT NULL AND :#{#updatedProduct.name} <> '' THEN :#{#updatedProduct.name} ELSE p.name END, " +
            "p.description = CASE WHEN :#{#updatedProduct.description} IS NOT NULL AND :#{#updatedProduct.description} <> '' THEN :#{#updatedProduct.description} ELSE p.description END, " +
            "p.price = CASE WHEN :#{#updatedProduct.price} IS NOT NULL THEN :#{#updatedProduct.price} ELSE p.price END, " +
            "p.quantity = CASE WHEN :#{#updatedProduct.quantity} IS NOT NULL THEN :#{#updatedProduct.quantity} ELSE p.quantity END " +
            "WHERE p.id = :id")
    Long updateProduct(@Param("id") Long id, @Param("updatedProduct") Product updatedProduct);
}
