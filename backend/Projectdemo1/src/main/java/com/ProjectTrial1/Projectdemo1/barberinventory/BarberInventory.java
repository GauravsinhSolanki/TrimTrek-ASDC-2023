// Package declaration
package com.ProjectTrial1.Projectdemo1.barberinventory;

// Importing necessary libraries from Lombok and JPA
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Annotating the class with Lombok annotations to automatically generate boilerplate code
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Entity // Marks this class as a JPA entity, representing a table in the database
@Table(name = "barber_inventory") // Specifies the table name in the database
@NoArgsConstructor // Default constructor without arguments
@AllArgsConstructor // Constructor with all arguments
public class BarberInventory {
    // Primary key for the entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Properties representing columns in the "barber_inventory" table
    private String barberId; // ID of the barber associated with the inventory item
    private String productId; // ID of the product in the inventory
    private String productName; // Name of the product in the inventory
    private int quantity; // Quantity of the product in the inventory
    private String description; // Description of the product in the inventory
}
