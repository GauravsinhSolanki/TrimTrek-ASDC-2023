package org.example;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double totalPrice;
    private Double offerDiscount;

    private Double tax;
    private Double loyaltyDiscount;
    private Double finalPrice;

    public void generateBill(User user, Barber barber, Offer offer) {

        //TODO: add service.getPrice() to calculate total price
        this.totalPrice = 0.0;

        if (offer != null) {
            this.offerDiscount = offer.getDiscount();

        }else {
            this.offerDiscount = 0.0;
        }

        if (user.getLoyaltyPoints() >= totalPrice) {

            this.loyaltyDiscount = totalPrice;
        } else {
            this.loyaltyDiscount = user.getLoyaltyPoints().doubleValue();
        }

        // Tax is 5% in halifax
        this.tax = 0.05 * totalPrice;

        this.finalPrice = totalPrice + tax - offerDiscount - loyaltyDiscount;

        // Deduct loyalty points from user redeemed it
        if (loyaltyDiscount > 0) {
            user.setLoyaltyPoints(user.getLoyaltyPoints() - loyaltyDiscount.intValue());
        }
    }
}
