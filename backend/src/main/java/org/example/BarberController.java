package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/barbers")
public class BarberController {

    private final BarberService barberService;

    @Autowired
    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    @GetMapping
    public List<Barber> getAllBarbers() {
        return barberService.getAllBarbers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barber> getBarberById(@PathVariable(value = "id") Long barberId) {
        Optional<Barber> barber = barberService.getBarberById(barberId);
        if(barber.isPresent()){
            return ResponseEntity.ok().body(barber.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Barber createBarber(@RequestBody Barber barber) {
        return barberService.createBarber(barber);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barber> updateBarber(@PathVariable(value = "id") Long barberId, @RequestBody Barber barberDetails) {
        Optional<Barber> barberOptional = barberService.getBarberById(barberId);
        if(!barberOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Optional<Barber> updatedBarber = barberService.updateBarber(barberId, barberDetails);
        return updatedBarber.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarber(@PathVariable(value = "id") Long barberId) {
        boolean isRemoved = barberService.deleteBarber(barberId);
        if(!isRemoved){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
