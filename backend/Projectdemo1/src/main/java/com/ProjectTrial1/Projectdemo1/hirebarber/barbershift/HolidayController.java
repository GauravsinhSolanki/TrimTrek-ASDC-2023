package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
public class HolidayController {
    @Autowired
    private HolidayService holidayService;

    @GetMapping("/all-holiday")
    public List<String> getAllShifts() {
        return holidayService.getHolidayList();
    }

    @PostMapping("/add-holiday")
    public Holiday addHoliday(@RequestBody Holiday holiday) {
        return holidayService.addHoliday(holiday);
    }

    @GetMapping("/holiday/{festivalDate}")
    public Holiday getHolidayByFestivalDate(@PathVariable(name = "festivalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate festivalDate) {
        return holidayService.getHolidayByFestivalDate(festivalDate);
    }

    @GetMapping("/exists/{festivalDate}")
    public boolean checkIfDateExists(@PathVariable(name = "festivalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate festivalDate) {
        return holidayService.dateExistsOrNot(festivalDate);
    }


    @GetMapping("/all-bookedHoliday")
    public List<Holiday> getAllBookedHoliday() {
        return holidayService.getAllBookedHoliday();
    }

}
