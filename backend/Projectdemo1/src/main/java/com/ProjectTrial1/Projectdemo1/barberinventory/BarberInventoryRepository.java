// Package declaration
package com.ProjectTrial1.Projectdemo1.barberinventory;

// Importing necessary libraries
import java.util.List;

// BarberInventoryRepository interface defining data access contract
public interface BarberInventoryRepository {

    // Save a BarberInventory item in the inventory
    BarberInventory save(BarberInventory barberInventory);

    // Retrieve all BarberInventory items from the inventory
    List<BarberInventory> findAll();

    // Find a BarberInventory item by emailId (barberId)
    BarberInventory findByEmailId(String id);

    // Update the quantity of a product in the inventory by ID
    BarberInventory updateQuantity(String id, int newQuantity);

    // Find a BarberInventory item by barberId and individual productName
    BarberInventory findByBarberIdAndProductName(String barberId, String productNameIndividual);

    // Update the quantity of a product in the inventory by productName and barberId
    BarberInventory updateQuantityNew(String productNameIndividual, int updatedQuantity, String barberId);
}
