package dev.dwidi.productservice.dto;

import dev.dwidi.productservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
   private Long productId;
   private String productName;
   private String productDescription;
   private Double productPrice;

    public ProductResponseDTO(Product product) {
         this.productId = product.getProductId();
         this.productName = product.getProductName();
         this.productDescription = product.getProductDescription();
         this.productPrice = product.getProductPrice();
    }
}
