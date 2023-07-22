package org.example;

import org.springframework.beans.factory.annotation.Autowired;

public class LoyaltyService {

    @Autowired
    private UserServices userServices;

    public void addPoints(User user, int points) {
        int currentPoints = user.getLoyaltyPoints();
        user.setLoyaltyPoints(currentPoints + points);
        userServices.createUser(user);
    }

    public void redeemPoints(User user, int points) {

        int currentPoints = user.getLoyaltyPoints();

        if (currentPoints >= points) {
            user.setLoyaltyPoints(currentPoints - points);
            userServices.createUser(user);
        } else {
            throw new IllegalArgumentException("Not enough points");
        }
    }
}
