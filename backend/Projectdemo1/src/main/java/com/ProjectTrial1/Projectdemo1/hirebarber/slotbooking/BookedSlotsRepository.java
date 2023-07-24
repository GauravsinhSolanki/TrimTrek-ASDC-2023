package com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookedSlotsRepository extends JpaRepository<BookedSlots, Integer> {

    List<BookedSlots> findByServiceDateOrderByStartTimeAsc(LocalDate serviceDate);

    List<BookedSlots> findByCustomerIdOrderByServiceDateAsc(String customerId);

    List<BookedSlots> findByBarberIdAndServiceDate(String barberId, Optional<LocalDate> serviceDate);


    BookedSlots findByBookingId(String bookingId);

    List<BookedSlots> findByServiceDate(LocalDate serviceDate);

    List<BookedSlots> findByCustomerIdAndServiceDate(String customerId, LocalDate serviceDate);
}