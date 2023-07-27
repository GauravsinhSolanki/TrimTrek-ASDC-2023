package com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking;

import com.ProjectTrial1.Projectdemo1.hirebarber.barberservice.BarberService;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedSlots {



    @javax.persistence.Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @NonNull
    @Column(name = "booking_id")
    private String bookingId;

    @NonNull
    @Column(name = "customer_id")
    private String customerId;

    @NonNull
    @Column(name = "barber_Id")
    private String barberId;

    @Nullable
    private int serviceId;

    @NonNull
    @Column(name = "service_date")
    private LocalDate serviceDate;

    @NonNull
    @Column(name = "service_duration")
    private int serviceDuration;

    @NonNull
    @Column(name = "start_time")
    private LocalTime startTime;

    @NonNull
    @Column(name = "end_time")
    private LocalTime endTime;

    @Nullable
    @Column(name = "is_active")
    private boolean isActive = true;

    private String serviceZoneId;
}
