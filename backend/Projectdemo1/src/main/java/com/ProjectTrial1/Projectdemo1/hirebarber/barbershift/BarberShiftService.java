package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;

import com.ProjectTrial1.Projectdemo1.account.userrole.UserRoleServices;
import com.ProjectTrial1.Projectdemo1.hirebarber.barbershift.barbershiftdto.BarberShiftDto;
import com.ProjectTrial1.Projectdemo1.hirebarber.exception.InvalidInputException;
import com.ProjectTrial1.Projectdemo1.hirebarber.servicezone.ServiceZoneServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class BarberShiftService {
    private static final Logger LOG = LoggerFactory.getLogger(BarberShiftService.class);
    @Autowired
    private BarberShiftRepo OfferServicePersonRepository;

    @Autowired
    UserRoleServices userRoleServices;

    @Autowired
    ServiceZoneServices serviceZoneServices;

    @Value("${barber.service.start-time}")
    private String barberServiceStartTime;

    @Value("${barber.service.end-time}")
    private String barberServiceEndTime;

    public List<BarberShift> getAllShifts() {
        LOG.debug("getAllShifts : , ");
        return OfferServicePersonRepository.findAll();
    }

    public List<BarberShift> getMondaySlots() {
        LOG.debug("getMondaySlots : , ");
        List<BarberShift> mondayList = OfferServicePersonRepository.findByDayOfWeek(String.valueOf(DayOfWeek.MONDAY));
        LOG.debug("getMondaySlots mondayList : " + mondayList);
        return mondayList;
    }

    public List<BarberShift> getTuesdaySlots() {
        LOG.debug("getTuesdaySlots : , ");
        List<BarberShift> tuesList = OfferServicePersonRepository.findByDayOfWeek(String.valueOf(DayOfWeek.TUESDAY));
        LOG.debug("getTuesdaySlots tuesList : " + tuesList);
        return tuesList;
    }

    public List<BarberShift> getWednesdaySlots() {
        LOG.debug("getWednesdaySlots : , ");
        List<BarberShift> wednesdayList = OfferServicePersonRepository.findByDayOfWeek(String.valueOf(DayOfWeek.WEDNESDAY));
        LOG.debug("getWednesdaySlots wednesdayList :  " + wednesdayList);
        return wednesdayList;
    }

    public List<BarberShift> getThursdaySlots() {
        LOG.debug("getThursdaySlots : , ");
        List<BarberShift> thursdayList = OfferServicePersonRepository
                .findByDayOfWeek(String.valueOf(DayOfWeek.THURSDAY));
        LOG.debug("getThursdaySlots thursdayList :  " + thursdayList);
        return thursdayList;
    }

    public List<BarberShift> getFridaySlots() {
        LOG.debug("getFridaySlots : , ");
        List<BarberShift> fridayList = OfferServicePersonRepository.findByDayOfWeek(String.valueOf(DayOfWeek.FRIDAY));
        LOG.debug("getFridaySlots fridayList :  " + fridayList);
        return fridayList;
    }

    public List<BarberShift> getSaturdaySlots() {
        LOG.debug("getSaturdaySlots : , ");
        List<BarberShift> saturdayList = OfferServicePersonRepository
                .findByDayOfWeek(String.valueOf(DayOfWeek.SATURDAY));
        LOG.debug("getSaturdaySlots saturdayList :  " + saturdayList);
        return saturdayList;
    }

    public List<BarberShift> getSundaySlots() {
        LOG.debug("getSundaySlots : , ");
        List<BarberShift> sundayList = OfferServicePersonRepository.findByDayOfWeek(String.valueOf(DayOfWeek.SUNDAY));
        LOG.debug("getSundaySlots sundayList :  " + sundayList);
        return sundayList;
    }

    public BarberShift addShift(BarberShiftDto barberShiftDto) {
        LOG.debug("save offerServicePerson: " + barberShiftDto);
        LocalTime sT = LocalTime.parse(barberServiceStartTime);
        LocalTime eT = LocalTime.parse(barberServiceEndTime);

        if ((barberShiftDto.getStartTime().isBefore(sT) || barberShiftDto.getEndTime().isAfter(eT))) {
            LOG.error("Start time is not proper");
            throw new InvalidInputException("Start time is not proper");
        }

        if (!(
                (barberShiftDto.getDayOfWeek().equals(DayOfWeek.MONDAY.toString())) ||
                        (barberShiftDto.getDayOfWeek().equals(DayOfWeek.TUESDAY.toString())) ||
                        (barberShiftDto.getDayOfWeek().equals(DayOfWeek.WEDNESDAY.toString())) ||
                        (barberShiftDto.getDayOfWeek().equals(DayOfWeek.THURSDAY.toString())) ||
                        (barberShiftDto.getDayOfWeek().equals(DayOfWeek.FRIDAY.toString())) ||
                        (barberShiftDto.getDayOfWeek().equals(DayOfWeek.SATURDAY.toString())) ||
                        (barberShiftDto.getDayOfWeek().equals(DayOfWeek.SUNDAY.toString()))
        )) {
            LOG.error("Day of week is not acceptable");
            throw new InvalidInputException("Day of week is not acceptable");
        }


        BarberShift offerServicePerson = convertDtoToEntity(barberShiftDto);
        return OfferServicePersonRepository.save(offerServicePerson);
    }

    private BarberShift convertDtoToEntity(BarberShiftDto barberShiftDto) {
        LOG.debug("convertDtoToEntity barberShiftDto: " + barberShiftDto);
        BarberShift barberShift = new BarberShift();
        barberShift.setBarberId(barberShiftDto.getBarberId());
        barberShift.setDayOfWeek(barberShiftDto.getDayOfWeek());
        barberShift.setStartTime(barberShiftDto.getStartTime());
        barberShift.setEndTime(barberShiftDto.getEndTime());
        barberShift.setCreatedBy(barberShiftDto.getCreatedBy());
        barberShift.setCreatedOn(LocalDateTime.now());
        LOG.debug("convertDtoToEntity barberShift: " + barberShift);
        return barberShift;
    }

    public List<BarberShift> getBookedSlotByBarberId(String barberId) {
        LOG.debug("getBookedSlotByBarberId barberId: " + barberId);
        List<BarberShift> bookedSlotOfUser = OfferServicePersonRepository.findByBarberId(barberId);
        LOG.debug("getBookedSlotByBarberId bookedSlotOfUser: " + bookedSlotOfUser);
        return bookedSlotOfUser;
    }


}
