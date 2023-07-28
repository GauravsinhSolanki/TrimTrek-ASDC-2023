package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;

import static org.junit.jupiter.api.Assertions.*;
import com.ProjectTrial1.Projectdemo1.hirebarber.barbershift.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
class HolidayServiceTest {
    @Mock
    HolidayRepository holidayRepository;

    @InjectMocks
    HolidayService holidayService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetHolidayList() {
        List<String> result = holidayService.getHolidayList();
        assertEquals(holidayEnum.values().length, result.size());
    }

    @Test
    public void testAddHoliday() {
        Holiday holiday = new Holiday();
        holiday.setHolidayId(holidayEnum.DIWALI);
        when(holidayRepository.save(any(Holiday.class))).thenReturn(holiday);
        Holiday result = holidayService.addHoliday(holiday);
        verify(holidayRepository, times(1)).save(any(Holiday.class));
        assertEquals(holidayEnum.DIWALI, result.getHolidayId());
    }

    @Test
    public void testGetHolidayByFestivalDate() {
        LocalDate date = LocalDate.of(2023, 11, 13);
        Holiday holiday = new Holiday();
        holiday.setFestivalDate(date);
        holiday.setFestivalName("Diwali");
        when(holidayRepository.findByFestivalDate(date)).thenReturn(Arrays.asList(holiday));
        Holiday result = holidayService.getHolidayByFestivalDate(date);
        verify(holidayRepository, times(1)).findByFestivalDate(date);
        assertNotNull(result);
    }


    @Test
    public void testDateExistsOrNot() {
        LocalDate date = LocalDate.of(2023, 11, 13); // Diwali
        Holiday holiday = new Holiday();
        holiday.setFestivalDate(date);
        when(holidayRepository.findAll()).thenReturn(Arrays.asList(holiday));
        boolean result = holidayService.dateExistsOrNot(date);
        verify(holidayRepository, times(1)).findAll();
        assertTrue(result);
    }


    @Test
    public void testGetAllBookedHoliday() {
        when(holidayRepository.findAll()).thenReturn(Arrays.asList(new Holiday(), new Holiday()));
        List<Holiday> result = holidayService.getAllBookedHoliday();
        verify(holidayRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }
}