package dev.dwidi.productservice.service;

import dev.dwidi.productservice.dto.ProductRequestDTO;
import dev.dwidi.productservice.dto.ProductResponseDTO;
import dev.dwidi.productservice.dto.PublicResponseDTO;
import dev.dwidi.productservice.entity.Product;
import dev.dwidi.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public PublicResponseDTO<ProductResponseDTO> createProduct(ProductRequestDTO productRequestDTO) {
        if (productRepository.existsByProductName(productRequestDTO.getProductName()))
            return new PublicResponseDTO<>(400, "Product name is already taken", null);{
        }
        if (productRepository.existsByProductDescription(productRequestDTO.getProductDescription())) {
            return new PublicResponseDTO<>(400, "Product description is already taken", null);
        }

        Product product = new Product();
        product.setProductName(productRequestDTO.getProductName());
        product.setProductDescription(productRequestDTO.getProductDescription());
        product.setProductPrice(productRequestDTO.getProductPrice());
        productRepository.save(product);

        return new PublicResponseDTO<>(200, "Product created successfully", new ProductResponseDTO(product));
    }

    public PublicResponseDTO<List<ProductResponseDTO>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOs = products.stream().map(ProductResponseDTO::new).collect(Collectors.toList());
        return new PublicResponseDTO<>(200, "Products fetched successfully", productResponseDTOs);
    }

    public PublicResponseDTO<ProductResponseDTO> getProductById(Long id) {
        Product product = productRepository.findByProductId(id).orElse(null);
        if (product == null) {
            return new PublicResponseDTO<>(404, "Product not found", null);
        }
        return new PublicResponseDTO<>(200, "Product fetched successfully", new ProductResponseDTO(product));
    }
}
