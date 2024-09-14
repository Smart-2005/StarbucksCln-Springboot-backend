package com.projects.secondproject.service.impl;

import com.projects.secondproject.dto.request.RequestProductDto;
import com.projects.secondproject.dto.response.ResponseProductDto;
import com.projects.secondproject.entity.Product;
import com.projects.secondproject.repo.ProductRepository;
import com.projects.secondproject.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void saveProduct(RequestProductDto requestProductDto) throws IOException {
        Product product = Product.builder()
                .productName(requestProductDto.getProductName())
                .productDescription(requestProductDto.getProductDescription())
                .productPrice(requestProductDto.getProductPrice())
                .productQuantity(requestProductDto.getProductQuantity())
                .ProductImage(requestProductDto.getProductImage().getBytes())
                .build();
        productRepository.save(product);
    }

    @Override
    public List<ResponseProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ResponseProductDto(
                        product.getId(),
                        product.getProductName(),
                        product.getProductDescription(),
                        product.getProductPrice(),
                        product.getProductQuantity(),
                        product.getProductImage()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void updateProduct(Long id, RequestProductDto requestProductDto) throws IOException {
        var getProduct = productRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Product not found"));
        getProduct.setProductImage(requestProductDto.getProductImage().getBytes());
        getProduct.setProductName(requestProductDto.getProductName());
        getProduct.setProductDescription(requestProductDto.getProductDescription());
        getProduct.setProductPrice(requestProductDto.getProductPrice());
        getProduct.setProductQuantity(requestProductDto.getProductQuantity());
        productRepository.save(getProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getProductData(Long id) {
        return  productRepository.findById(id);
    }
}
