package com.projects.secondproject.dto.response;

import com.projects.secondproject.entity.Product;
import lombok.*;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private long id;
    private String productName;
    private String productDescription;
    private int productPrice;
    private int productQuantity;
    private String productImage; // Base64-encoded image

    // Constructor to convert Product entity to DTO
    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productPrice = product.getProductPrice();
        this.productQuantity = product.getProductQuantity();

        // Convert byte[] to Base64
        this.productImage = Base64.getEncoder().encodeToString(product.getProductImage());
    }
}
