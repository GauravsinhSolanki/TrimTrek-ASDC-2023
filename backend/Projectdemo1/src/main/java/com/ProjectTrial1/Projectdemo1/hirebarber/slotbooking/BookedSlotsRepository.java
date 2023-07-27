package com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking;

import com.ProjectTrial1.Projectdemo1.hirebarber.barberservice.BarberService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookedSlotsRepository  {

    List<BookedSlots> findByServiceDateOrderByStartTimeAsc(LocalDate serviceDate);

    List<BookedSlots> findByCustomerIdOrderByServiceDateAsc(String customerId);

    List<BookedSlots> findByBarberIdAndServiceDate(String barberId, Optional<LocalDate> serviceDate);

    BookedSlots findByBookingId(String bookingId);

    List<BookedSlots> findByServiceDate(LocalDate serviceDate);

    List<BookedSlots> findByCustomerIdAndServiceDate(String customerId, LocalDate serviceDate);

    BookedSlots save(BookedSlots bookedSlots);

    List<BookedSlots> findAll();
}