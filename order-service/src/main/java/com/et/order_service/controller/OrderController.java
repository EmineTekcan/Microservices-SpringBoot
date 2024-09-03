package com.et.order_service.controller;

import com.et.order_service.dto.OrderRequest;
import com.et.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest){
        System.out.println(orderRequest);
        orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(
                "Order successfully created", HttpStatus.CREATED);
    }


}
