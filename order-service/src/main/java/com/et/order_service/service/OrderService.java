package com.et.order_service.service;

import com.et.order_service.dto.InventoryResponse;
import com.et.order_service.dto.OrderLineItemsDto;
import com.et.order_service.dto.OrderRequest;
import com.et.order_service.model.Order;
import com.et.order_service.model.OrderLineItems;
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
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToOrderLineItems)
                .toList();

        Order order = Order.builder()
                .orderLineItemsList(orderLineItems)
                .orderNumber(UUID.randomUUID().toString())
                .build();

        List<String> skuCodes = order.getOrderLineItemsList()
                .stream().map(OrderLineItems::getSkuCode)
                .toList();

        InventoryResponse[] inventoryResponse = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder
                        .queryParam("skuCode", skuCodes.toArray())
                        .build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();



        boolean allProductsIsInStock= Arrays.stream(Objects.requireNonNull(inventoryResponse)).allMatch(InventoryResponse::isInStock);

       if (allProductsIsInStock && inventoryResponse.length>0){
           orderRepository.save(order);
       }
       else {
           throw new IllegalArgumentException("Product is not in stock ");
       }

    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }
}
