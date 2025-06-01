package com.example.inventoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/inventory")
public class InventoryController
{
    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/buses")
    public List<InventoryModel> getAllBuses() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/search")
    public List<InventoryModel> searchBySourceAndDestination(
            @RequestParam String source,
            @RequestParam String destination) {
        return inventoryRepository.findBySourceAndDestination(source, destination);
    }

    @PostMapping("/seats")
    public List<InventoryModel> searchBySourceDestinationAndSeats(@RequestBody SeatSearchRequest request) {
        return inventoryRepository.findAll().stream()
                .filter(bus -> request.getSource().equalsIgnoreCase(bus.getSource()) &&
                        request.getDestination().equalsIgnoreCase(bus.getDestination()) &&
                        bus.getAvailableSeats() >= request.getRequestedSeats())
                .toList();
    }
}
