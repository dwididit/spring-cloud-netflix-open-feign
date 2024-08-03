package dev.dwidi.transactionservice.entity.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
}
