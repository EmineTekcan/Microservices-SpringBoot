package com.et.order_service.service;

import com.et.order_service.client.InventoryClient;
import com.et.order_service.dto.OrderRequest;
import com.et.order_service.model.Order;
import com.et.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        boolean isInStock= inventoryClient.isInStock(orderRequest.getSkuCode(),orderRequest.getQuantity());

        if (isInStock){
           Order order= Order.builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .skuCode(orderRequest.getSkuCode())
                    .price(orderRequest.getPrice())
                    .quantity(orderRequest.getQuantity())
                    .build();
           orderRepository.save(order);
        }else {
            throw new RuntimeException("Product is not in stock with id: "+orderRequest.getSkuCode());
        }

    }


}
