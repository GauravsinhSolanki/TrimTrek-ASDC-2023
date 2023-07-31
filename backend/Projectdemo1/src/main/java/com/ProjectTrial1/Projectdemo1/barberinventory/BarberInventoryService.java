// Package declaration
package com.ProjectTrial1.Projectdemo1.barberinventory;

// Importing necessary libraries and annotations
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Annotating the class as a Spring Service, indicating it's a service component
@Service
public class BarberInventoryService {

    // Autowiring the BarberInventoryRepository for data access
    @Autowired
    BarberInventoryRepository barberInventoryRepository;

    // Method to save a BarberInventory item in the inventory
    public BarberInventory saveBarberInventory(BarberInventory barberInventory) {
        // Generate a unique product ID and set it in the BarberInventory object
        String generatedString = generateUniqueProductId();
        barberInventory.setProductId(generatedString);

        // Save the BarberInventory object to the database using the repository
        return barberInventoryRepository.save(barberInventory);
    }

    // Helper method to generate a unique product ID
    private String generateUniqueProductId() {
        String generatedProductId = RandomStringUtils.random(11, true, true);
        return generatedProductId;
    }

    // Method to get all BarberInventory items from the inventory
    public List<BarberInventory> getAllBarberInventories() {
        return barberInventoryRepository.findAll();
    }

    // Method to find a BarberInventory item by barberId and productName
    public BarberInventory findByBarberIdAndProductName(String emailId, String productName) {
        return barberInventoryRepository.findByBarberIdAndProductName(emailId, productName);
    }

    // Method to reduce the quantity of a product in the inventory
    public BarberInventory reduceProductQuantity(String id, int quantityToReduce) {
        BarberInventory barberInventory = barberInventoryRepository.findByEmailId(id);

        if (barberInventory != null) {
            int currentQuantity = barberInventory.getQuantity();
            if (currentQuantity >= quantityToReduce) {
                int newQuantity = currentQuantity - quantityToReduce;
                barberInventory.setQuantity(newQuantity);
                // Update the reduced quantity in the database using the repository
                return barberInventoryRepository.updateQuantity(id, newQuantity);
            } else {
                throw new IllegalArgumentException("Insufficient quantity for product with ID: " + id);
            }
        } else {
            throw new IllegalArgumentException("Product with ID: " + id + " not found in the inventory.");
        }
    }

    // Method to perform deduction of products for a specific service
    public BarberInventory performDeductionOperation(String barberId, String serviceName) {
        BarberInventory barberInventory = null;
        String[] products = new String[0];

        // Determine the list of products to be deducted based on the service name
        if (serviceName.equals("Haircut")) {
            products = new String[]{"Shampoo", "Scissor"};
        } else if (serviceName.equals("Beard")) {
            products = new String[]{"Scissor", "ShavingCream"};
        } else if (serviceName.equals("MustacheGrooming")) {
            products = new String[]{"Comb", "BeardOil"};
        } else if (serviceName.equals("Facials")) {
            products = new String[]{"FaceCleanser", "FacialMask"};
        } else if (serviceName.equals("Hair Treatment")) {
            products = new String[]{"HairConditioners", "HairSpray"};
        }

        // Deduct the quantity of each product from the inventory
        for (String productNameIndividual : products) {
            barberInventory = barberInventoryRepository.findByBarberIdAndProductName(barberId, productNameIndividual);
            int updatedQuantity = barberInventory.getQuantity() - 1;
            barberInventory.setQuantity(updatedQuantity);
            barberInventoryRepository.updateQuantityNew(productNameIndividual, updatedQuantity, barberId);
        }

        return barberInventory;
    }
}
