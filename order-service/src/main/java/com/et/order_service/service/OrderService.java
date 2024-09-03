package com.et.order_service.service;

import com.et.order_service.dao.OrderRequest;
import com.et.order_service.model.Order;
import com.et.order_service.model.OrderLineItems;
import com.et.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
       List<OrderLineItems> orderLineItemsList= orderRequest.getOrderLineItemsList()
                .stream()
                .map(this::mapToOrderLineItems)
                .toList();

       Order order= Order.builder()
               .orderLineItemsList(orderLineItemsList)
               .orderNumber(UUID.randomUUID().toString())
               .build();
       orderRepository.save(order);
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItems orderLineItemsDto) {
       return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }
}
