package com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking;

import com.ProjectTrial1.Projectdemo1.hirebarber.barbershift.BarberShift;
import com.ProjectTrial1.Projectdemo1.hirebarber.barbershift.BarberShiftService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookedSlotsHelperClass {
    private static final Logger LOG = LoggerFactory.getLogger(BookedSlotsServices.class);




    @Autowired
    BarberShiftService offerServicePersonService;




    List<BarberShift> getshift(LocalDate serviceDate) {

        LOG.debug("getshift serviceDate : " + serviceDate);

        int datofweek = serviceDate.getDayOfWeek().getValue();
        LOG.debug("getshift datofweek : " + datofweek);

        List<BarberShift> mondayList = offerServicePersonService.getMondaySlots();
        List<BarberShift> tuesdayList = offerServicePersonService.getTuesdaySlots();
        List<BarberShift> wednesdayList = offerServicePersonService.getWednesdaySlots();
        List<BarberShift> thursdayList = offerServicePersonService.getThursdaySlots();
        List<BarberShift> FridayList = offerServicePersonService.getFridaySlots();
        List<BarberShift> saturdayList = offerServicePersonService.getSaturdaySlots();
        List<BarberShift> sundayList = offerServicePersonService.getSundaySlots();

        List<BarberShift> shits = null;
        if (datofweek == 1) {
            shits = mondayList;
        }
        if (datofweek == 2) {
            shits = tuesdayList;
        }
        if (datofweek == 3) {
            shits = wednesdayList;
        }
        if (datofweek == 4) {
            shits = thursdayList;
        }
        if (datofweek == 5) {
            shits = FridayList;
        }
        if (datofweek == 6) {
            shits = saturdayList;
        }
        if (datofweek == 7) {
            shits = sundayList;
        }

        LOG.debug("getshift shits : " + shits);
        return shits;
    }
}
