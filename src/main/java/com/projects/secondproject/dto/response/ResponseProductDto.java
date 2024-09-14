package com.projects.secondproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseProductDto {

    private long id;
    private String productName;
    private String productDescription;
    private int productPrice;
    private int productQuantity;
    private byte[] productImage;
}

