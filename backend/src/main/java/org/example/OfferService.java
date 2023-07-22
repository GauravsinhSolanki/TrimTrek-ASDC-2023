package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getAllOffersForBarber(Barber barber) {
        return offerRepository.findByBarber(barber);
    }

    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

}
