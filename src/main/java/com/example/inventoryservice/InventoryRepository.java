package com.example.inventoryservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<InventoryModel, String> {
    List<InventoryModel> findBySourceAndDestination(String source, String destination);
    InventoryModel findByBusNumber(String BusNumber);
}
