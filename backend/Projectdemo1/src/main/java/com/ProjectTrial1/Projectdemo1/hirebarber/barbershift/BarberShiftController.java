package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;

import com.ProjectTrial1.Projectdemo1.hirebarber.barbershift.barbershiftdto.BarberShiftDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/barber")
public class BarberShiftController {

	@Autowired
	private BarberShiftService offerServicePersonService;

	@GetMapping("/all-shifts")
	public List<BarberShift> getAllShifts() {
		return offerServicePersonService.getAllShifts();
	}

	@PostMapping("/add-shift")
	public BarberShift addShift(@RequestBody BarberShiftDto barberShiftDto) {
		return offerServicePersonService.addShift(barberShiftDto);
	}

	@GetMapping("/booked-shift/{barberId}")
	public List<BarberShift> getBookedSlotByBarberId(@PathVariable String barberId) {
		return offerServicePersonService.getBookedSlotByBarberId(barberId);
	}

}
