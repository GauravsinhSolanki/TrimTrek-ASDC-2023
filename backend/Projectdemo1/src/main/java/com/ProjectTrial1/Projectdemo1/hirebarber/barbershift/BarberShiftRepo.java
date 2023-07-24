package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarberShiftRepo extends JpaRepository<BarberShift, Integer> {
	List<BarberShift> findByDayOfWeek(String dayOfWeek);

	List<BarberShift> findByBarberId(String barberId);

}
