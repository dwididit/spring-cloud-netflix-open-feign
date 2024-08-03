package dev.dwidi.productservice.controller;

import dev.dwidi.productservice.dto.ProductRequestDTO;
import dev.dwidi.productservice.dto.ProductResponseDTO;
import dev.dwidi.productservice.dto.PublicResponseDTO;
import dev.dwidi.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public PublicResponseDTO<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        log.info("Creating product with name: {}", productRequestDTO.getProductName());
        return productService.createProduct(productRequestDTO);
    }

    @GetMapping("/all")
    public PublicResponseDTO<List<ProductResponseDTO>> getAllProducts() {
        log.info("Fetching all products");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public PublicResponseDTO<ProductResponseDTO> getProductById(@PathVariable Long id) {
        log.info("Fetching product with id: {}", id);
        return productService.getProductById(id);
    }
}
