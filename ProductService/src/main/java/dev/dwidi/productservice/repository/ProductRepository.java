package dev.dwidi.productservice.repository;

import dev.dwidi.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductId(Long productId);
    Optional<Product> findByProductName(String productName);
    Optional<Product> findByProductDescription(String productDescription);
    Boolean existsByProductName(String productName);
    Boolean existsByProductDescription(String productDescription);
}
