package com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking.Dto;

import lombok.*;

import java.time.LocalTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSlotsDto {
    private String barberId;
    private LocalTime startTime;
    private LocalTime endTime;
}
