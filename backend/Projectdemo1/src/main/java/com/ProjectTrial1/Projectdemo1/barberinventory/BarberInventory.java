package com.ProjectTrial1.Projectdemo1.barberinventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "barber_inventory")
@NoArgsConstructor
@AllArgsConstructor
public class BarberInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String barberId;
    private String productId;
    private String productName;
    private int quantity;
    private String description;
}

