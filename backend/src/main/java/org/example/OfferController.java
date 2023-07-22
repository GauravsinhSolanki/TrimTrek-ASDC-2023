package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/barber/{barberId}")
    public ResponseEntity<List<Offer>> getAllOffersForBarber(@PathVariable Long barberId) {
        Barber barber = new Barber();
        barber.setBarber_id(barberId);
        return ResponseEntity.ok(offerService.getAllOffersForBarber(barber));
    }

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        return ResponseEntity.ok(offerService.createOffer(offer));
    }
}
