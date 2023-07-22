package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberService {

    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private OfferService offerService;

    @Autowired
    public BarberService(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    public Optional<Barber> getBarberById(Long barberId) {
        return barberRepository.findById(barberId);
    }

    public Barber createBarber(Barber barber) {
        return barberRepository.save(barber);
    }

    public Optional<Barber> updateBarber(Long barberId, Barber barberDetails) {
        Optional<Barber> barberOptional = barberRepository.findById(barberId);
        if(!barberOptional.isPresent()){
            return Optional.empty();
        }

        Barber barber = barberOptional.get();
        barber.setFirstName(barberDetails.getFirstName());
        barber.setLastName(barberDetails.getLastName());
        barber.setAddress(barberDetails.getAddress());
        barber.setPhone(barberDetails.getPhone());
        barber.setEmail(barberDetails.getEmail());
        barber.setPassword(barberDetails.getPassword());
        barber.setSpecialtyHaircuts(barberDetails.getSpecialtyHaircuts());
        barber.setSpecialtyServices(barberDetails.getSpecialtyServices());
        barber.setShifts(barberDetails.getShifts());

        final Barber updatedBarber = barberRepository.save(barber);
        return Optional.of(updatedBarber);
    }

    public boolean deleteBarber(Long barberId) {
        Optional<Barber> barber = barberRepository.findById(barberId);
        if(barber.isPresent()){
            barberRepository.delete(barber.get());
            return true;
        }
        return false;
    }

    public List<Offer> getOffersForBarber(Long barberId) {
        Barber barber = barberRepository.findById(barberId).orElse(null);
        if(barber != null) {
            return offerService.getAllOffersForBarber(barber);
        }
        return null;
    }
}
