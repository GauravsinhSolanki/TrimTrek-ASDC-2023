package com.ProjectTrial1.Projectdemo1.barberinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BarberInventoryServiceTest {

    @Mock
    BarberInventoryRepository barberInventoryRepository;

    @InjectMocks
    BarberInventoryService barberInventoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveBarberInventory() {
        // Given
        BarberInventory barberInventory = new BarberInventory();
        barberInventory.setBarberId("barberId");
        barberInventory.setProductId("productId");
        barberInventory.setProductName("productName");
        barberInventory.setQuantity(10);
        barberInventory.setDescription("description");

        // When
        when(barberInventoryRepository.save(barberInventory)).thenReturn(barberInventory);
        BarberInventory result = barberInventoryService.saveBarberInventory(barberInventory);

        // Then
        assertEquals(barberInventory, result);
        verify(barberInventoryRepository, times(1)).save(barberInventory);
    }

    @Test
    public void testGetAllBarberInventories() {
        // Given
        BarberInventory barberInventory1 = new BarberInventory();
        BarberInventory barberInventory2 = new BarberInventory();
        List<BarberInventory> barberInventories = Arrays.asList(barberInventory1, barberInventory2);

        // When
        when(barberInventoryRepository.findAll()).thenReturn(barberInventories);
        List<BarberInventory> result = barberInventoryService.getAllBarberInventories();

        // Then
        assertEquals(barberInventories, result);
        verify(barberInventoryRepository, times(1)).findAll();
    }

    @Test
    public void testGetBarberInventoryByEmailId() {
        // Given
        String id = "testId";
        BarberInventory barberInventory = new BarberInventory();
        barberInventory.setBarberId(id);

        // When
        when(barberInventoryRepository.findByEmailId(id)).thenReturn(barberInventory);
        BarberInventory result = barberInventoryService.getBarberInventoryByEmailId(id);

        // Then
        assertEquals(barberInventory, result);
        verify(barberInventoryRepository, times(1)).findByEmailId(id);
    }

    @Test
    public void testReduceProductQuantity() {
        // Given
        String id = "testId";
        int quantityToReduce = 5;
        BarberInventory barberInventory = new BarberInventory();
        barberInventory.setBarberId(id);
        barberInventory.setQuantity(10);

        // When
        when(barberInventoryRepository.findByEmailId(id)).thenReturn(barberInventory);
        when(barberInventoryRepository.updateQuantity(id, 5)).thenReturn(barberInventory);
        BarberInventory result = barberInventoryService.reduceProductQuantity(id, quantityToReduce);

        // Then
        assertEquals(barberInventory, result);
        verify(barberInventoryRepository, times(1)).findByEmailId(id);
        verify(barberInventoryRepository, times(1)).updateQuantity(id, 5);
    }
}