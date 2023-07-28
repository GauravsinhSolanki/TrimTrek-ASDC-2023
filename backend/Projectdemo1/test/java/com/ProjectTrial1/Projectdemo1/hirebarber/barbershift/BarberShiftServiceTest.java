package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;
import com.ProjectTrial1.Projectdemo1.hirebarber.barbershift.barbershiftdto.BarberShiftDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class BarberShiftServiceTest {

    @Mock
    BarberShiftRepository barberShiftRepository;

    @InjectMocks
    BarberShiftService barberShiftService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllShifts() {
        when(barberShiftRepository.findAll()).thenReturn(Arrays.asList(new BarberShift(), new BarberShift()));
        List<BarberShift> result = barberShiftService.getAllShifts();
        verify(barberShiftRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetMondaySlots() {
        when(barberShiftRepository.findByDayOfWeek(DayOfWeek.MONDAY.name())).thenReturn(Arrays.asList(new BarberShift()));
        List<BarberShift> result = barberShiftService.getMondaySlots();
        verify(barberShiftRepository, times(1)).findByDayOfWeek(DayOfWeek.MONDAY.name());
        assertEquals(1, result.size());
    }

    @Test
    public void testGetTuesdaySlots() {
        when(barberShiftRepository.findByDayOfWeek(DayOfWeek.TUESDAY.name())).thenReturn(Arrays.asList(new BarberShift()));
        List<BarberShift> result = barberShiftService.getTuesdaySlots();
        verify(barberShiftRepository, times(1)).findByDayOfWeek(DayOfWeek.TUESDAY.name());
        assertEquals(1, result.size());
    }

    @Test
    public void testGetWednesdaySlots() {
        when(barberShiftRepository.findByDayOfWeek(DayOfWeek.WEDNESDAY.name())).thenReturn(Arrays.asList(new BarberShift()));
        List<BarberShift> result = barberShiftService.getWednesdaySlots();
        verify(barberShiftRepository, times(1)).findByDayOfWeek(DayOfWeek.WEDNESDAY.name());
        assertEquals(1, result.size());
    }

    @Test
    public void testGetThursdaySlots() {
        when(barberShiftRepository.findByDayOfWeek(DayOfWeek.THURSDAY.name())).thenReturn(Arrays.asList(new BarberShift()));
        List<BarberShift> result = barberShiftService.getThursdaySlots();
        verify(barberShiftRepository, times(1)).findByDayOfWeek(DayOfWeek.THURSDAY.name());
        assertEquals(1, result.size());
    }

    @Test
    public void testGetFridaySlots() {
        when(barberShiftRepository.findByDayOfWeek(DayOfWeek.FRIDAY.name())).thenReturn(Arrays.asList(new BarberShift()));
        List<BarberShift> result = barberShiftService.getFridaySlots();
        verify(barberShiftRepository, times(1)).findByDayOfWeek(DayOfWeek.FRIDAY.name());
        assertEquals(1, result.size());
    }

    @Test
    public void testGetSaturdaySlots() {
        when(barberShiftRepository.findByDayOfWeek(DayOfWeek.SATURDAY.name())).thenReturn(Arrays.asList(new BarberShift()));
        List<BarberShift> result = barberShiftService.getSaturdaySlots();
        verify(barberShiftRepository, times(1)).findByDayOfWeek(DayOfWeek.SATURDAY.name());
        assertEquals(1, result.size());
    }

    @Test
    public void testGetSundaySlots() {
        when(barberShiftRepository.findByDayOfWeek(DayOfWeek.SUNDAY.name())).thenReturn(Arrays.asList(new BarberShift()));
        List<BarberShift> result = barberShiftService.getSundaySlots();
        verify(barberShiftRepository, times(1)).findByDayOfWeek(DayOfWeek.SUNDAY.name());
        assertEquals(1, result.size());
    }


    @Test
    public void testGetBookedSlotByBarberId() {
        when(barberShiftRepository.findByBarberId("barber1")).thenReturn(Arrays.asList(new BarberShift()));
        List<BarberShift> result = barberShiftService.getBookedSlotByBarberId("barber1");
        verify(barberShiftRepository, times(1)).findByBarberId("barber1");
        assertEquals(1, result.size());
    }

}