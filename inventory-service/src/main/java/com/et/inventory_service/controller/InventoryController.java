package com.et.inventory_service.controller;

import com.et.inventory_service.dto.InventoryRequest;
import com.et.inventory_service.dto.InventoryResponse;
import com.et.inventory_service.model.Inventory;
import com.et.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<Boolean> isInStock(@RequestParam String skuCode, @RequestParam Integer quantity){
        return new ResponseEntity<>(inventoryService.isInStock(skuCode,quantity), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> getAllProducts(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.addInventoryItem(inventoryRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
