package com.ProjectTrial1.Projectdemo1.barberinventory;

import java.util.List;

public interface BarberInventoryRepository  {
    BarberInventory save(BarberInventory barberInventory);

    List<BarberInventory> findAll();

    BarberInventory findByEmailId(String id);

    BarberInventory updateQuantity(String id, int newQuantity);
}
