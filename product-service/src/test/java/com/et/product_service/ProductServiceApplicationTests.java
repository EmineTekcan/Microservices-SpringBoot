package com.et.product_service;

import com.et.product_service.dto.ProductRequest;
import com.et.product_service.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

@SpringBootTest
@Testcontainers
class ProductServiceApplicationTests {

//	static MongoDBContainer mongoDBContainer= new MongoDBContainer("mongo:4.0.10");
//	static ProductService productService;
//
//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
//		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//	}
//
//
//	void shouldAddProduct(){
//
//		ProductRequest productRequest=ProductRequest.builder()
//				.name("Iphone 13")
//				.description("iphone 13")
//				.price(BigDecimal.valueOf(139989))
//				.build();
//
//
//	}
}
