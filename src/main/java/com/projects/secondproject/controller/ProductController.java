package com.projects.secondproject.controller;

import com.projects.secondproject.dto.request.RequestProductDto;
import com.projects.secondproject.dto.response.ResponseProductDto;
import com.projects.secondproject.entity.Product;
import com.projects.secondproject.entity.Role;
import com.projects.secondproject.service.ProductService;
import com.projects.secondproject.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin(allowedHeaders = "*" ,origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private  Role role;


    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveProduct(
            @ModelAttribute RequestProductDto requestProductDto
    ) throws IOException {
        productService.saveProduct(requestProductDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Product Save",requestProductDto.getProductName()),
                HttpStatus.CREATED
        ) ;
    }

    @GetMapping("/all-products")
    public ResponseEntity<StandardResponse> getAllProducts(){

            List<ResponseProductDto> productDtos = productService.getAllProducts();
            return new ResponseEntity<>(
                    new StandardResponse(200,"Your Products Here",productDtos),
                    HttpStatus.OK
            ) ;

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<StandardResponse> updateProduct(
            @PathVariable Long id,
            @ModelAttribute RequestProductDto requestProductDto
    ) throws IOException {
        productService.updateProduct(id, requestProductDto);
        return new ResponseEntity<>(
                new StandardResponse(200," Product Updated",requestProductDto.getProductName()),
                HttpStatus.OK
        ) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getProductData(
            @PathVariable Long id
    ){
        Optional<Product> product = productService.getProductData(id);
        return new ResponseEntity<>(
                new StandardResponse(200,"Here your  Product Data", product),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteProduct(
            @PathVariable Long id
    ){
        productService.deleteProduct(id);
        return new ResponseEntity<>(
                new StandardResponse(200,"Product Deleted",id),
                HttpStatus.OK
        );
    }
}
