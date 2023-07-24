package com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.Dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveBookingDto {
    private String barberId;
    private LocalDate leaveDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
