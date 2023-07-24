package com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking;

import com.ProjectTrial1.Projectdemo1.hirebarber.exception.BookedSlotException;
import com.ProjectTrial1.Projectdemo1.hirebarber.exception.HolidayExistException;
import com.ProjectTrial1.Projectdemo1.hirebarber.exception.InvalidInputException;
import com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.Dto.AvailableSlotsDto;
import com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.Dto.LeaveBookingDto;
import com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.Dto.SlotBookingDto;
import com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.Dto.ViewBookedSlotsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;



import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
public class BookedSlotsController {

    @Autowired
    private BookedSlotsServices bookedSlotsServices;




    @PostMapping("/bookDto-slot")
    public BookedSlots bookDtoSlot(@RequestBody SlotBookingDto slotBookingDto) throws HolidayExistException, InvalidInputException, BookedSlotException {
        return bookedSlotsServices.bookDtoSlot(slotBookingDto);
    }

    @GetMapping("/slots-for-given-request/{serviceDuration}/{serviceDate}/{customerId}")
    public List<AvailableSlotsDto> getAllSlotForGivenRequest(@PathVariable Integer serviceDuration, @PathVariable(name = "serviceDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate serviceDate , @PathVariable String customerId  ) throws InvalidInputException, ParseException {
        return bookedSlotsServices.getAllSlotForGivenRequest(serviceDuration, serviceDate , customerId );
    }

    @GetMapping("/eliminate-bookedSlots/{serviceDate}")
    public List<BookedSlots> getEliminateBookedSlot(@PathVariable(name = "serviceDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate serviceDate) throws ParseException {
        return bookedSlotsServices.eliminateBookedSlot(serviceDate);
    }


    @GetMapping("/admin/booked-slots/{serviceDate}")
    public List<BookedSlots> getBookedSlotOfThatDate(@PathVariable(name = "serviceDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate serviceDate) throws ParseException {

        return bookedSlotsServices.bookSlotForGivenRequest(serviceDate);
    }

    @GetMapping("/admin/{customerId}")
    public List<BookedSlots> getBookedSlotByCustomerId(@PathVariable String customerId) throws Exception {
        return bookedSlotsServices.getBookedSlotByCustomerId(customerId);
    }

    @GetMapping(value = {"/gardner/{barberId}/{serviceDate}", "/barber/{barberId}"})
    @ResponseBody
    public List<BookedSlots> getBookedSlotByBarberId(@PathVariable String barberId, @PathVariable(name = "serviceDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> serviceDate) {
        return bookedSlotsServices.getBookedSlotByBarberId(barberId, serviceDate);
    }

    @GetMapping("/{bookingId}")
    public BookedSlots invalid(@PathVariable String bookingId) {
        return bookedSlotsServices.makeInvalid(bookingId);
    }

    @PostMapping("/book-leave")
    public BookedSlots requestForLeave(@RequestBody LeaveBookingDto leaveBookingDto) throws BookedSlotException {
        return bookedSlotsServices.addLeave(leaveBookingDto);
    }

    @GetMapping("/all-leaves")
    public List<BookedSlots> getAllLeave() {
        return bookedSlotsServices.getAllLeave();
    }

    @GetMapping("/admin/booked-slots")
    public List<ViewBookedSlotsDto> getAllBookedSlot()  {
        return bookedSlotsServices.getAllBookedSlot();
    }


}