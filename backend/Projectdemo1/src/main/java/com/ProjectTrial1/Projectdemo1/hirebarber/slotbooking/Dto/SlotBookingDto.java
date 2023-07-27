package com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.Dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotBookingDto {


    private String serviceId ;
    private String serviceName ;
    private LocalDate serviceDate;
    private int serviceDuration;
    private LocalTime startTime;
    private LocalTime endTime;
    private String customerId;
    private String barberId;


}
