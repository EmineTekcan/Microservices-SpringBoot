package com.et.inventory_service.service;

import com.et.inventory_service.dto.InventoryRequest;
import com.et.inventory_service.dto.InventoryResponse;
import com.et.inventory_service.model.Inventory;
import com.et.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode, Integer quantity) {

        return inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);

    }

    public void addInventoryItem(InventoryRequest inventoryRequest) {
        Inventory inventory = Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();

        inventoryRepository.save(inventory);
    }

}
