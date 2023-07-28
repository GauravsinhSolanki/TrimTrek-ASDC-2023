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

    public BarberInventory findByBarberIdAndProductName(String emailId , String productName) {
        return barberInventoryRepository.findByBarberIdAndProductName(emailId,productName);
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

                throw new IllegalArgumentException("Insufficient quantity for product with ID: " + id);
            }
        } else {

            throw new IllegalArgumentException("Product with ID: " + id + " not found in the inventory.");
        }
    }

    public BarberInventory performDeductionOperation(String barberId, String serviceName) {
        System.out.println("Perform deduction");
        BarberInventory barberInventory = null;
        String[] products = new String[0];
        System.out.println("0"+ products);
        if (serviceName.equals("Haircut")) {
          products= new String[]{"Shampoo", "Scissor"};
            System.out.println("1" + products);
        } else if (serviceName.equals("Beard") ) {
         products = new String[]{"Scissor", "ShavingCream"};
            System.out.println("2" + products);
        } else if (serviceName.equals("MustacheGrooming") ) {
         products = new String[]{"Comb", "BeardOil"};
        } else if (serviceName.equals("facials") ) {
            products = new String[]{"FaceCleanser", "FacialMask"};
            System.out.println("3" + products);
        } else if (serviceName.equals("Hair Treatment") ) {
            products = new String[]{"HairConditioners", "HairSpray"};
            System.out.println("4" + products);
        }

        for (String productNameIndividual : products) {
            System.out.println("for loop");
            barberInventory = barberInventoryRepository.findByBarberIdAndProductName(barberId, productNameIndividual);
            System.out.println(barberInventory);
            int updatedQuantity = barberInventory.getQuantity() - 1;
            System.out.println(updatedQuantity);
            barberInventory.setQuantity(updatedQuantity);
          barberInventoryRepository.updateQuantityNew(productNameIndividual, updatedQuantity,barberId);

        }

        return barberInventory;
    }
}
