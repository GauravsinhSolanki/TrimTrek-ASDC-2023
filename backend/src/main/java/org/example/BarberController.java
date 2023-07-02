package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/barbers")
public class BarberController {

    private final BarberRepository barberRepository;

    @Autowired
    public BarberController(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    @GetMapping
    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barber> getBarberById(@PathVariable(value = "id") Long barberId) {
        Optional<Barber> barber = barberRepository.findById(barberId);
        if(barber.isPresent()){
            return ResponseEntity.ok().body(barber.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Barber createBarber(@RequestBody Barber barber) {
        return barberRepository.save(barber);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barber> updateBarber(@PathVariable(value = "id") Long barberId, @RequestBody Barber barberDetails) {
        Optional<Barber> barberOptional = barberRepository.findById(barberId);
        if(!barberOptional.isPresent()){
            return ResponseEntity.notFound().build();
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
        return ResponseEntity.ok(updatedBarber);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarber(@PathVariable(value = "id") Long barberId) {
        Optional<Barber> barber = barberRepository.findById(barberId);
        if(!barber.isPresent()){
            return ResponseEntity.notFound().build();
        }

        barberRepository.delete(barber.get());
        return ResponseEntity.ok().build();
    }
}
