package com.ProjectTrial1.Projectdemo1.barberinventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barber-inventory")
@CrossOrigin("*")
public class BarberInventoryController {
    @Autowired
    BarberInventoryService barberInventoryService;

    @PostMapping
    public BarberInventory addBarberInventory(@RequestBody BarberInventory barberInventory) {
        return barberInventoryService.saveBarberInventory(barberInventory);
    }

    @GetMapping
    public List<BarberInventory> getAllBarberInventories() {
        return barberInventoryService.getAllBarberInventories();
    }

    @GetMapping("/get-inventory/{emailId}/{productName}")
    public BarberInventory getBarberInventoryByEmailIdAndProductName(@PathVariable String emailId, @PathVariable String productName) {
        return barberInventoryService.findByBarberIdAndProductName(emailId,productName);
    }

    @PutMapping("/reduce-quantity/{id}/{quantity}")
    public ResponseEntity<BarberInventory> reduceProductQuantity(
            @PathVariable String id,
            @PathVariable int quantity) {
        try {
            BarberInventory updatedInventory = barberInventoryService.reduceProductQuantity(id, quantity);
            if (updatedInventory != null) {
                return ResponseEntity.ok(updatedInventory);
            } else {
                // The service method returned null, which means the operation was not successful.
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (IllegalArgumentException e) {
            // Exception occurred in the service method, handle the error appropriately.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
