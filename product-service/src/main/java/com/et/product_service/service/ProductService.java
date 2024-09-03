package com.et.product_service.service;

import com.et.product_service.dto.ProductRequest;
import com.et.product_service.dto.ProductResponse;
import com.et.product_service.model.Product;
import com.et.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        System.out.println(productRequest);
        Product product= Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        System.out.println(product);
        productRepository.save(product);
        log.info("Product {} is saved ",product.getId());
    }

    public List<ProductResponse>  getAllProducts(){
        List<Product> products= productRepository.findAll();
        System.out.println(products);
       return products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
