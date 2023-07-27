package com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking;


import com.ProjectTrial1.Projectdemo1.EmailService;
import com.ProjectTrial1.Projectdemo1.contact.AddressDto;
import com.ProjectTrial1.Projectdemo1.contact.AddressServices;
import com.ProjectTrial1.Projectdemo1.hirebarber.barberservice.BarberServiceJdbcRepository;
import com.ProjectTrial1.Projectdemo1.hirebarber.barbershift.BarberShift;
import com.ProjectTrial1.Projectdemo1.hirebarber.barbershift.BarberShiftService;
import com.ProjectTrial1.Projectdemo1.hirebarber.barbershift.HolidayService;
import com.ProjectTrial1.Projectdemo1.hirebarber.exception.BookedSlotException;
import com.ProjectTrial1.Projectdemo1.hirebarber.exception.BookingLimitException;
import com.ProjectTrial1.Projectdemo1.hirebarber.exception.HolidayExistException;
import com.ProjectTrial1.Projectdemo1.hirebarber.exception.InvalidInputException;
import com.ProjectTrial1.Projectdemo1.hirebarber.barberservice.BarberServiceServices;
import com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.Dto.AvailableSlotsDto;
import com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.Dto.LeaveBookingDto;
import com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.Dto.SlotBookingDto;
import com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.Dto.ViewBookedSlotsDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookedSlotsServices {
    private static final Logger LOG = LoggerFactory.getLogger(BookedSlotsServices.class);
    @Autowired
    private BookedSlotsRepository bookedSlotsRepository;


    private List<BarberShift> list;

    @Value("${error.message.holiday-exist}")
    private String holidayExistError;

    @Value("${error.message.end-time-less}")
    private String endTimeError;

    @Value("${error.message.booked-slot}")
    private String bookedSlotError;

    @Value("${error.message.past-date}")
    private String pastDateError;

    @Value("${error.message.duration}")
    private String durationError;

    @Value("${daily.booking.limit.counter}")
    private int bookingLimitCount;

    @Value("${error.message.booking.limit}")
    private String bookingLimit;

    @Autowired
    BarberShiftService offerServicePersonService;

    @Autowired
    HolidayService holidayService;

    @Autowired
    BookedSlotsHelperClass bookedSlotsHelperClass;

    @Autowired
    BarberServiceServices barberServiceServices;

    @Autowired
    AddressServices addressServices;

    @Autowired
    BarberServiceJdbcRepository barberServiceJdbcRepository;

    @Autowired
    private EmailService emailService;

    public List<BookedSlots> bookSlotForGivenRequest(LocalDate serviceDate) {
        LOG.debug("bookSlotForGivenRequest serviceDate : " + serviceDate);
        List<BookedSlots> list = bookedSlotsRepository.findByServiceDateOrderByStartTimeAsc(serviceDate);
        LOG.debug("bookSlotForGivenRequest list : " + list);
        return list;
    }


    public List<AvailableSlotsDto> getAllSlotForGivenRequest(Integer serviceDuration, LocalDate serviceDate, String customerId) throws InvalidInputException, ParseException {


        LOG.debug("getAllSlotForThisRequest serviceDuration : " + serviceDuration);
        List<BookedSlots> slots = new ArrayList<>();


        List<AddressDto> addressOfCustomer = addressServices.getAddressByUserId(customerId);

//        if (holidayService.dateExistsOrNot(serviceDate) == true) {
//            LOG.error("There is a holiday, slot cannot be booked");
//            throw new HolidayExistException(holidayExistError);
//        }

        LocalDate today = LocalDate.now();
        if (serviceDate.isBefore(today)) {
            LOG.error("Past date is not valid");
            throw new InvalidInputException(pastDateError);
        }

        if (serviceDuration == 0 || serviceDuration == null) {
            LOG.error("ServiceDuration cannot be 0 OR null");
            throw new InvalidInputException(durationError);
        }


        List<BookedSlots> subshifts = eliminateBookedSlot(serviceDate);


        for (BookedSlots c : subshifts) {
            LocalTime sT = c.getStartTime();
            LocalTime eT = c.getEndTime();

            Duration dur = Duration.between(sT, eT);
            long v = dur.toMinutes();


            if (v >= serviceDuration) {
                while (sT.compareTo(eT) < 0) {

                    BookedSlots timeslot = new BookedSlots();

                    timeslot.setStartTime(sT);

                    sT = sT.plusMinutes(serviceDuration);
                    timeslot.setEndTime(sT);

                    LocalTime now = LocalTime.now();
//                    if (!(timeslot.getEndTime().compareTo(eT) > 0) && (now.isAfter(timeslot.getStartTime()))) {

                        timeslot.setBarberId(c.getBarberId());

                        List<AddressDto> addressOfBarber = addressServices.getAddressByUserId(c.getBarberId());


//                        for (AddressDto x : addressOfCustomer) {
//                            for (AddressDto Y : addressOfBarber){
//                            if (x.getServiceZoneId() == Y.getServiceZoneId()) {
                                slots.add(timeslot);
//                            }
//                            }
//                        }


                        LOG.debug("getAllSlotForThisRequest timeslot : " + timeslot);
//                    }

                    sT = timeslot.getEndTime();
                    LOG.debug("getAllSlotForThisRequest sT : " + sT);
                }
            }
        }

        LOG.debug("getAllSlotForThisRequest slots : " + slots);
        List<AvailableSlotsDto> finalSlots = convertEntityToDtoAvailableSlots(slots);
        LOG.debug("getAllSlotForThisRequest finalSlots : " + finalSlots);
        return finalSlots;
    }

    private List<AvailableSlotsDto> convertEntityToDtoAvailableSlots(List<BookedSlots> slots) {
        List<AvailableSlotsDto> list = new ArrayList<>();

        for (BookedSlots loop : slots) {
            AvailableSlotsDto availableSlotsDto = new AvailableSlotsDto();


            availableSlotsDto.setBarberId(loop.getBarberId());
            availableSlotsDto.setStartTime(loop.getStartTime());
            availableSlotsDto.setEndTime(loop.getEndTime());
            LOG.debug("convertEntityToDtoAvailableSlots availableSlotsDto : " + availableSlotsDto);
            list.add(availableSlotsDto);
        }
        LOG.debug("convertEntityToDtoAvailableSlots list : " + list);
        return list;
    }


    public List<BookedSlots> eliminateBookedSlot(LocalDate serviceDate) throws ParseException {

        LOG.debug("eliminateBookedSlot serviceDate : " + serviceDate);
        List<BookedSlots> onthatdateslot = bookedSlotsRepository.findByServiceDateOrderByStartTimeAsc(serviceDate);
        LOG.debug("eliminateBookedSlot onthatdateslot : " + onthatdateslot);

        List<BarberShift> allShifts = bookedSlotsHelperClass.getshift(serviceDate);
        LOG.debug("eliminateBookedSlot allShifts : " + allShifts);

        List<BookedSlots> ll = getsubshift(allShifts, onthatdateslot);
        LOG.debug("eliminateBookedSlot ll : " + ll);
        return ll;
    }

    public List<BookedSlots> getsubshift(List<BarberShift> allShifts, List<BookedSlots> onthatdateslot) {
        LOG.debug("getsubshift allShifts : " + allShifts);
        LOG.debug("getsubshift onthatdateslot : " + onthatdateslot);

        List<BookedSlots> fl = new ArrayList<>();

        for (BarberShift b : allShifts) {
            LocalTime sst = b.getStartTime();
            LocalTime lt1 = b.getEndTime();
            for (BookedSlots c : onthatdateslot) {

                if ((c.getStartTime().isAfter(sst) && c.getEndTime().isBefore(lt1)) || c.getStartTime().compareTo(sst) == 0 || c.getEndTime().compareTo(lt1) == 0) {
                    BookedSlots timeAvailableSlot = new BookedSlots();
                    timeAvailableSlot.setStartTime(sst);
                    timeAvailableSlot.setEndTime(c.getStartTime());


                    if (!(sst.compareTo(c.getStartTime()) == 0)) {

                        if (!(timeAvailableSlot.getStartTime() == timeAvailableSlot.getEndTime())) {


                            timeAvailableSlot.setBarberId(b.getBarberId());


//                            timeAvailableSlot.setServiceZoneId(b.getServiceZoneId().getServiceZoneId());

                            System.out.println("heeeyyyyyyy hererererer" + b.getBarberId());
                            fl.add(timeAvailableSlot);
                            LOG.debug("getsubshift timeAvailableSlot : " + timeAvailableSlot);

                        }

                    }
                    sst = c.getEndTime();
                    LOG.debug("getsubshift sst : " + sst);
                }
            }

            BookedSlots timeAvailableSlot2 = new BookedSlots();
            timeAvailableSlot2.setStartTime(sst);
            timeAvailableSlot2.setEndTime(lt1);


            if (!(timeAvailableSlot2.getStartTime() == timeAvailableSlot2.getEndTime()))
                LOG.debug("getsubshift timeAvailableSlot2 : " + timeAvailableSlot2);

            timeAvailableSlot2.setBarberId(b.getBarberId());


//            timeAvailableSlot2.setServiceZoneId(b.getServiceZoneId().getServiceZoneId());
            System.out.println("heeeyyyyyyy 2222222" + b.getBarberId());
            fl.add(timeAvailableSlot2);
        }

        LOG.debug("getsubshift fl : " + fl);
        return fl;
    }

    private List<BookedSlots> isBookedOrNot(BookedSlots timeAvailableSlot, List<BookedSlots> bookedSlotOfThatDate) {

        LOG.debug("isBookedOrNot timeAvailableSlot : " + timeAvailableSlot);
        List<BookedSlots> list = new ArrayList<>();
        for (BookedSlots loop : bookedSlotOfThatDate) {


            int sameStartTime = loop.getStartTime().compareTo(timeAvailableSlot.getStartTime());
            int sameEndTime = loop.getEndTime().compareTo(timeAvailableSlot.getEndTime());
            boolean startTimeCompare = (timeAvailableSlot.getStartTime().isAfter(loop.getStartTime()) || timeAvailableSlot.getStartTime().isBefore(loop.getEndTime()));
            boolean endTimeCompare = (timeAvailableSlot.getEndTime().isAfter(loop.getStartTime()) || timeAvailableSlot.getEndTime().isBefore(loop.getEndTime()));


            if (!(sameStartTime == 0) && !(sameEndTime == 0) && !(startTimeCompare) && !(endTimeCompare)) {
                LOG.debug("isBookedOrNot timeAvailableSlot : " + timeAvailableSlot);
                list.add(timeAvailableSlot);
            }
        }

        LOG.debug("isBookedOrNot list : " + list);
        return list;
    }

    private List<BookedSlots> getShiftsTimeSlots(Integer serviceDuration, List<BarberShift> allShifts, LocalDate serviceDate) {
        LOG.debug("getShiftsTimeSlots serviceDuration : " + serviceDuration);
        LOG.debug("getShiftsTimeSlots allShifts : " + allShifts);
        LOG.debug("getShiftsTimeSlots serviceDate : " + serviceDate);

        List<BookedSlots> listfinal = new ArrayList<>();


        for (BarberShift a : allShifts) {

            LocalTime sT = a.getStartTime();
            LocalTime eT = a.getEndTime();

            while (sT.compareTo(eT) < 0) {

                BookedSlots timeAvailableSlot = new BookedSlots();
                BookedSlots timeAvailableSlot1 = new BookedSlots();
                timeAvailableSlot.setStartTime(sT);

                sT = sT.plusMinutes(serviceDuration);

                timeAvailableSlot.setEndTime(sT);
                timeAvailableSlot.setServiceDuration(serviceDuration);

                List<BookedSlots> bookedSlotOfThatDate = bookSlotForGivenRequest(serviceDate);

                LOG.debug("getShiftsTimeSlots bookedSlotOfThatDate : " + bookedSlotOfThatDate);


                if (bookedSlotOfThatDate.isEmpty()) {

                    LocalTime now = LocalTime.now();
                    LOG.debug("getShiftsTimeSlots now : " + now);
                    if (!now.isAfter(timeAvailableSlot.getStartTime())) {
                        listfinal.add(timeAvailableSlot);

                    }
                }


                if (bookedSlotOfThatDate.size() > 0) {

                    List<BookedSlots> thisIsFinalList = isBookedOrNot(timeAvailableSlot, bookedSlotOfThatDate);

                    for (BookedSlots d : thisIsFinalList) {
                        timeAvailableSlot1.setStartTime(d.getStartTime());
                        timeAvailableSlot1.setEndTime(d.getEndTime());
                    }
                    LOG.debug("getShiftsTimeSlots timeAvailableSlot1 : " + timeAvailableSlot1);
                    listfinal.add(timeAvailableSlot1);

                }
            }

        }
        LOG.debug("getShiftsTimeSlots listfinal : " + listfinal);
        return listfinal;
    }

    public List<BookedSlots> getBookedSlotByCustomerId(String customerId) throws Exception {

        LOG.debug("getBookedSlotByCustomerId customerId : " + customerId);

        List<BookedSlots> bookedSlotOfCustomer = bookedSlotsRepository.findByCustomerIdOrderByServiceDateAsc(customerId);


        LOG.debug("getBookedSlotByCustomerId bookedSlotOfCustomer : " + bookedSlotOfCustomer);
        return bookedSlotOfCustomer;

    }


    public List<BookedSlots> getBookedSlotByBarberId(String barberId, Optional<LocalDate> serviceDate) {

        LOG.debug("getBookedSlotByBarberId barberId : " + barberId);
        LOG.debug("getBookedSlotByBarberId serviceDate : " + serviceDate);
        if (!(serviceDate.isPresent())) {
            serviceDate = Optional.of(LocalDate.now());
        }

        List<BookedSlots> bookedSlotOfUserOnServiceDate = bookedSlotsRepository.findByBarberIdAndServiceDate(barberId, serviceDate);

        LOG.debug("getBookedSlotByBarberId bookedSlotOfUserOnServiceDate : " + bookedSlotOfUserOnServiceDate);
        return bookedSlotOfUserOnServiceDate;

    }

//    public BookedSlots makeInvalid(String bookingId) {
//
//        LOG.debug("makeInvalid bookingId : " + bookingId);
//
//        BookedSlots bookedSlots = bookedSlotsRepository.findByBookingId(bookingId);
//
////        bookedSlots.setActive(false);
//        bookedSlotsRepository.save(bookedSlots);
//
//        LOG.debug("makeInvalid bookedSlots : " + bookedSlots);
//        return bookedSlots;
//    }

    public BookedSlots addLeave(LeaveBookingDto leaveBookingDto) throws BookedSlotException {


        LOG.debug("addLeave leaveBookingDto : " + leaveBookingDto);

        List<BookedSlots> aa = bookedSlotsRepository.findByBarberIdAndServiceDate(leaveBookingDto.getBarberId(), Optional.ofNullable(leaveBookingDto.getLeaveDate()));
        for (BookedSlots loop : aa) {

            int sameStartTime = loop.getStartTime().compareTo(leaveBookingDto.getStartTime());
            int sameEndTime = loop.getEndTime().compareTo(leaveBookingDto.getEndTime());
            boolean startTimeCompare = (leaveBookingDto.getStartTime().isAfter(loop.getStartTime()) || leaveBookingDto.getStartTime().isBefore(loop.getEndTime()));
            boolean endTimeCompare = (leaveBookingDto.getEndTime().isAfter(loop.getStartTime()) || leaveBookingDto.getEndTime().isBefore(loop.getEndTime()));


            if ((sameStartTime == 0) || (sameEndTime == 0) || startTimeCompare || endTimeCompare) {

                LOG.error("You are already booked, you can't take dayOff");
                throw new BookedSlotException(bookedSlotError);
            }

        }

        LOG.debug("addLeave leaveBookingDto : " + leaveBookingDto);
        BookedSlots bookedSlots = convertDtoToEntityLeave(leaveBookingDto);
        LOG.debug("addLeave bookedSlots : " + bookedSlots);
        return bookedSlotsRepository.save(bookedSlots);

    }

    private BookedSlots convertDtoToEntityLeave(LeaveBookingDto leaveBookingDto) {

        BookedSlots bookedSlots = new BookedSlots();
        bookedSlots.setBarberId(leaveBookingDto.getBarberId());
        bookedSlots.setServiceDate(leaveBookingDto.getLeaveDate());
        bookedSlots.setStartTime(leaveBookingDto.getStartTime());
        bookedSlots.setEndTime(leaveBookingDto.getEndTime());
        LOG.debug("convertDtoToEntityLeave bookedSlots : " + bookedSlots);
        return bookedSlots;
    }


    public List<BookedSlots> getAllLeave() {

        List<BookedSlots> list = new ArrayList<>();

        List<BookedSlots> bookedSlotAndLeave = bookedSlotsRepository.findAll();
        LOG.debug("getAllLeave bookedSlotAndLeave : " + bookedSlotAndLeave);

        for (BookedSlots loop : bookedSlotAndLeave) {
            LOG.debug("getAllLeave loop : " + loop);
            if (loop.getServiceId() == 0 ) {
                list.add(loop);
            }
        }
        LOG.debug("getAllLeave list : " + list);
        return list;
    }


    public BookedSlots bookDtoSlot(SlotBookingDto slotBookingDto) throws HolidayExistException, InvalidInputException, BookedSlotException {

        LOG.debug("bookDtoSlot slotBookingDto : " + slotBookingDto);


//        if (holidayService.dateExistsOrNot(slotBookingDto.getServiceDate()) == true) {
//            LOG.error("There is a holiday, slot cannot be booked");
//            throw new HolidayExistException(holidayExistError);
//        }

        if (slotBookingDto.getEndTime().compareTo(slotBookingDto.getStartTime()) < 1) {
            LOG.error("EndTime should not be less than StartTime");
            throw new InvalidInputException(endTimeError);
        }

        List<BookedSlots> bookedDate = bookedSlotsRepository.findByServiceDate(slotBookingDto.getServiceDate());
        LOG.debug("bookDtoSlot bookedDate : " + bookedDate);

        int bookedSlotByDateAndCustomerIdSize = bookedSlotsRepository.findByCustomerIdAndServiceDate(slotBookingDto.getCustomerId(), slotBookingDto.getServiceDate()).size();
        LOG.debug("bookDtoSlot bookedDateAndCustomerSize : " + bookedSlotByDateAndCustomerIdSize);

        if ((bookingLimitCount <= bookedSlotByDateAndCustomerIdSize)) {
            LOG.error("You have reached you daily booking limit");
            throw new BookingLimitException(bookingLimit);
        }


        if (bookedDate.isEmpty()) {
            BookedSlots bookedSlots = convertDtoToEntitySlotBooking(slotBookingDto);
//            bookedSlots.setTravelDuration(customerSiteTravel);
            bookedSlots.setBookingId(generateBookingId(barberServiceJdbcRepository.findById(bookedSlots.getServiceId()).getServiceId(), bookedSlots.getServiceDate(), bookedSlots.getCustomerId()));
            LOG.debug("bookDtoSlot bookedSlots : " + bookedSlots);
            return bookedSlotsRepository.save(bookedSlots);
        }
        if (!(bookedDate.isEmpty())) {
            for (BookedSlots y : bookedDate) {


                int sameStartTime = y.getStartTime().compareTo(slotBookingDto.getStartTime());
                LOG.debug("bookDtoSlot sameStartTime : " + sameStartTime);

                int sameEndTime = y.getEndTime().compareTo(slotBookingDto.getEndTime());
                LOG.debug("bookDtoSlot sameEndTime  :  " + sameEndTime);

                boolean startTimeCompare = slotBookingDto.getStartTime().isAfter(y.getStartTime()) && slotBookingDto.getStartTime().isBefore(y.getEndTime());
                LOG.debug("bookDtoSlot startTimeCompare : " + startTimeCompare);

                boolean endTimeCompare = slotBookingDto.getEndTime().isAfter(y.getStartTime()) && slotBookingDto.getEndTime().isBefore(y.getEndTime());
                LOG.debug("bookDtoSlot endTimeCompare : " + endTimeCompare);

                if ((sameStartTime == 0) || (sameEndTime == 0) || (startTimeCompare) || (endTimeCompare)) {
                    LOG.error("This slot is already booked, please select another slot or date ");
                    throw new BookedSlotException(bookedSlotError);

                }
            }
        }

        BookedSlots bookedSlots = convertDtoToEntitySlotBooking(slotBookingDto);
//        bookedSlots.setTravelDuration(customerSiteTravel);
        bookedSlots.setBookingId(generateBookingId(barberServiceJdbcRepository.findById(bookedSlots.getServiceId()).getServiceId(), bookedSlots.getServiceDate(), bookedSlots.getCustomerId()));
        LOG.debug("bookDtoSlot bookedSlots : " + bookedSlots);

        String toEmail = slotBookingDto.getCustomerId();
        String subject = "Appointment Confirmation at TrimTrek";
        String emailText = "Your appointment has been booked successfully on " + slotBookingDto.getServiceDate() + " from " + slotBookingDto.getStartTime() + " to " + slotBookingDto.getEndTime() + ".\n\nThank you for choosing our service!";

        emailService.sendEmail(toEmail, subject, emailText);

        return bookedSlotsRepository.save(bookedSlots);
    }

    private String generateBookingId(String serviceId, LocalDate serviceDate, String customerId) {
        String generatedString = RandomStringUtils.randomNumeric(3);
        LOG.debug("generateBookingId generatedString : " + generatedString);
        String mergeString = serviceDate.toString().concat(serviceId).concat(customerId).concat(generatedString);
        LOG.debug("generateBookingId mergeString : " + mergeString);
        return mergeString;
    }

    private BookedSlots convertDtoToEntitySlotBooking(SlotBookingDto slotBookingDto) {
        BookedSlots bookedSlots = new BookedSlots();

        bookedSlots.setCustomerId(slotBookingDto.getCustomerId());
        bookedSlots.setServiceDate(slotBookingDto.getServiceDate());
        bookedSlots.setServiceDuration(slotBookingDto.getServiceDuration());
        bookedSlots.setStartTime(slotBookingDto.getStartTime());
        bookedSlots.setEndTime(slotBookingDto.getEndTime());
        bookedSlots.setBarberId(slotBookingDto.getBarberId());
        bookedSlots.setServiceId(barberServiceServices.getBarberServiceByServiceId(slotBookingDto.getServiceId()).getId());

        LOG.debug("convertDtoToEntity bookedSlots : " + bookedSlots);

        return bookedSlots;
    }

    private SlotBookingDto convertEntityToDtoSlotBooking(BookedSlots bookedSlots) {
        SlotBookingDto slotBookingDto = new SlotBookingDto();

        slotBookingDto.setCustomerId(bookedSlots.getCustomerId());
        slotBookingDto.setServiceDate(bookedSlots.getServiceDate());
        slotBookingDto.setServiceDuration(bookedSlots.getServiceDuration());
        slotBookingDto.setStartTime(bookedSlots.getStartTime());
        slotBookingDto.setEndTime(bookedSlots.getEndTime());
        slotBookingDto.setBarberId(bookedSlots.getBarberId());
        slotBookingDto.setServiceId(barberServiceJdbcRepository.findById(bookedSlots.getServiceId()).getServiceId());
        LOG.debug("convertEntityToDto slotBookingDto : " + slotBookingDto);

        return slotBookingDto;
    }

    public List<ViewBookedSlotsDto> getAllBookedSlot() {
        List<ViewBookedSlotsDto> finalList = new ArrayList<>();
        List<BookedSlots> list = bookedSlotsRepository.findAll();
        LOG.debug("getAllBookedSlot list : " + list);


        for (BookedSlots loop : list) {

            if (loop.getServiceId() == 0) {
                ViewBookedSlotsDto viewBookedSlotsDto = new ViewBookedSlotsDto();
                viewBookedSlotsDto.setBarberId(loop.getBarberId());
                viewBookedSlotsDto.setServiceDate(loop.getServiceDate());
                viewBookedSlotsDto.setStartTime(loop.getStartTime());
                viewBookedSlotsDto.setEndTime(loop.getEndTime());
                LOG.debug("getAllBookedSlot viewBookedSlotsDto : " + viewBookedSlotsDto);
                finalList.add(viewBookedSlotsDto);
            }

            if (!(loop.getServiceId() == 0)) {
                ViewBookedSlotsDto viewBookedSlotsDto = new ViewBookedSlotsDto();
                viewBookedSlotsDto.setBookingId(loop.getBookingId());
                viewBookedSlotsDto.setServiceId(barberServiceJdbcRepository.findById(loop.getServiceId()).getServiceId());
                viewBookedSlotsDto.setServiceName(barberServiceJdbcRepository.findById(loop.getServiceId()).getServiceName());
                viewBookedSlotsDto.setServiceDate(loop.getServiceDate());
                viewBookedSlotsDto.setServiceDuration(loop.getServiceDuration());
                viewBookedSlotsDto.setStartTime(loop.getStartTime());
                viewBookedSlotsDto.setEndTime(loop.getEndTime());
                viewBookedSlotsDto.setCustomerId(loop.getCustomerId());
                viewBookedSlotsDto.setBarberId(loop.getBarberId());
                LOG.debug("getAllBookedSlot viewBookedSlotsDto : " + viewBookedSlotsDto);
                finalList.add(viewBookedSlotsDto);
            }
        }


        LOG.debug("getAllBookedSlot finalList : " + finalList);
        return finalList;
    }
}







