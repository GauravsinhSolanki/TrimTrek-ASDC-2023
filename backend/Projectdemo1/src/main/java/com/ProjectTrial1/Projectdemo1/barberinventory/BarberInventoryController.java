// Package declaration
package com.ProjectTrial1.Projectdemo1.barberinventory;

// Importing necessary Spring Framework libraries
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// The class is annotated as a REST Controller, handling HTTP requests
@RestController
@RequestMapping("/barber-inventory") // Base URL mapping for all endpoints in this controller
@CrossOrigin("*") // Allows cross-origin requests from any origin (for development purposes)
public class BarberInventoryController {
    // Autowiring the BarberInventoryService, which will handle business logic
    @Autowired
    BarberInventoryService barberInventoryService;

    // HTTP POST request to add a new BarberInventory item
    @PostMapping
    public BarberInventory addBarberInventory(@RequestBody BarberInventory barberInventory) {
        return barberInventoryService.saveBarberInventory(barberInventory);
    }

    // HTTP GET request to retrieve all BarberInventory items
    @GetMapping
    public List<BarberInventory> getAllBarberInventories() {
        return barberInventoryService.getAllBarberInventories();
    }

    // HTTP GET request to retrieve a specific BarberInventory item by emailId and productName
    @GetMapping("/get-inventory/{emailId}/{productName}")
    public BarberInventory getBarberInventoryByEmailIdAndProductName(@PathVariable String emailId, @PathVariable String productName) {
        return barberInventoryService.findByBarberIdAndProductName(emailId, productName);
    }

    // HTTP PUT request to reduce the quantity of a specific BarberInventory item
    @PutMapping("/reduce-quantity/{id}/{quantity}")
    public ResponseEntity<BarberInventory> reduceProductQuantity(
            @PathVariable String id,
            @PathVariable int quantity) {
        try {
            // Attempt to reduce the product quantity using the BarberInventoryService
            BarberInventory updatedInventory = barberInventoryService.reduceProductQuantity(id, quantity);

            // Check if the operation was successful and return the updated BarberInventory
            if (updatedInventory != null) {
                return ResponseEntity.ok(updatedInventory);
            } else {
                // The service method returned null, which means the operation was not successful.
                // Return a BAD_REQUEST response to indicate failure.
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (IllegalArgumentException e) {
            // An exception occurred in the service method, handle the error appropriately.
            // Return a BAD_REQUEST response and provide a null body to indicate failure.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
