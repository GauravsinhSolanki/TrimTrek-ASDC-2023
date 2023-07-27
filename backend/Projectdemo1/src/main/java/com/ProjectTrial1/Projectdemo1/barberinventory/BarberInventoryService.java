package com.ProjectTrial1.Projectdemo1.barberinventory;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BarberInventoryService {



    @Autowired
    BarberInventoryRepository barberInventoryRepository;

    public BarberInventory saveBarberInventory(BarberInventory barberInventory) {
        String generatedString = generateUniqueUserId();

        barberInventory.setProductId(generatedString);
        return barberInventoryRepository.save(barberInventory);
    }
    private String generateUniqueUserId() {
        String generatedProductId = RandomStringUtils.random(11, true, true);
        return generatedProductId;
    }
    public List<BarberInventory> getAllBarberInventories() {
        return barberInventoryRepository.findAll();
    }

    public BarberInventory getBarberInventoryByEmailId(String id) {
        return barberInventoryRepository.findByEmailId(id);
    }

    public BarberInventory reduceProductQuantity(String id, int quantityToReduce) {
        BarberInventory barberInventory = barberInventoryRepository.findByEmailId(id);

        if (barberInventory != null) {
            int currentQuantity = barberInventory.getQuantity();
            if (currentQuantity >= quantityToReduce) {
                int newQuantity = currentQuantity - quantityToReduce;
                barberInventory.setQuantity(newQuantity);
                return barberInventoryRepository.updateQuantity(id, newQuantity);
            } else {
                // Handle insufficient quantity here (e.g., throw an exception, return an error message, etc.)
                throw new IllegalArgumentException("Insufficient quantity for product with ID: " + id);
            }
        } else {
            // Handle product not found here (e.g., throw an exception, return an error message, etc.)
            throw new IllegalArgumentException("Product with ID: " + id + " not found in the inventory.");
        }
    }
}
