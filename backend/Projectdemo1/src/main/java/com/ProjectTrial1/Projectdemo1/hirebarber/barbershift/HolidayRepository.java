package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
    List<Holiday> findByFestivalDate(LocalDate festivalDate);
}
