package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Service
public class HolidayService {
    private static final Logger LOG = LoggerFactory.getLogger(HolidayService.class);
    @Autowired
    HolidayRepository holidayRepository;

    public List<String> getHolidayList() {

        List<String> holidayList = new ArrayList<>();
        List<holidayEnum> enumList = new ArrayList<holidayEnum>(EnumSet.allOf(holidayEnum.class));
        LOG.debug("getHolidayList, enumList:" + enumList);
        for (holidayEnum type : enumList) {
            String dayOfHoliday = type.getHolidayName();
            holidayList.add(dayOfHoliday);
        }
        LOG.debug("getHolidayList, holidayList:" + holidayList);

        return holidayList;
    }


    public Holiday addHoliday(Holiday holiday) {
        LOG.debug("addHoliday holiday: " + holiday);
        return holidayRepository.save(holiday);
    }

    public Holiday getHolidayByFestivalDate(LocalDate festivalDate) {
        LOG.debug("getHolidayByFestivalDate festivalDate: " + festivalDate);
        List<Holiday> holiday = holidayRepository.findByFestivalDate(festivalDate);
        LOG.debug("getHolidayByFestivalDate list: " + holiday);

        Holiday newholiday = new Holiday();
        for (Holiday v : holiday) {
            newholiday.setId(v.getId());
            newholiday.setHolidayId(v.getHolidayId());
            newholiday.setFestivalDate(v.getFestivalDate());
            newholiday.setFestivalName(v.getFestivalName());
            newholiday.setCreatedBy(v.getCreatedBy());
            newholiday.setCreatedOn(v.getCreatedOn());
            newholiday.setUpdatedBy(v.getUpdatedBy());
            newholiday.setUpdatedOn(v.getUpdatedOn());
        }
        LOG.debug("getHolidayByFestivalDate newholiday: " + newholiday);
        return newholiday;
    }


    public boolean dateExistsOrNot(LocalDate festivalDate) {
        List<Holiday> list = holidayRepository.findAll();
        LOG.debug("dateExistsOrNot list: " + list);
        for (Holiday loop : list) {

            if (loop.getFestivalDate().compareTo(festivalDate) == 0) {
                return true;
            }

        }

        return false;
    }


    public List<Holiday> getAllBookedHoliday() {

        List<Holiday> list = holidayRepository.findAll();
        LOG.debug("getAllBookedHoliday list: " + list);
        return list;
    }
}