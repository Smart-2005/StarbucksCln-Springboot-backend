package com.projects.secondproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestProductDto {

    private String productName;
    private String productDescription;
    private int productPrice;
    private int productQuantity;
    private MultipartFile ProductImage;
}
