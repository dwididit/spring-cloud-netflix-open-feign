package dev.dwidi.transactionservice.client;

import dev.dwidi.transactionservice.dto.PublicResponseDTO;
import dev.dwidi.transactionservice.entity.dto.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/product/{id}")
    PublicResponseDTO<ProductResponseDTO> getProductById(@PathVariable("id") Long id);
}