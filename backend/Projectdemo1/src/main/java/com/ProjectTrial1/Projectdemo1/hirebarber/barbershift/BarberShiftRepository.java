package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;

import java.util.List;

public interface BarberShiftRepository {
	List<BarberShift> findAll();
	List<BarberShift> findByDayOfWeek(String dayOfWeek);
	List<BarberShift> findByBarberId(String barberId);
	BarberShift save(BarberShift barberShift);

}
