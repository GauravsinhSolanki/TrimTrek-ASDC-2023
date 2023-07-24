package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift.barbershiftdto;

import lombok.*;

import java.time.LocalTime;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BarberShiftDto {
    private String barberId;
    private LocalTime startTime;
    private LocalTime endTime;
    private String dayOfWeek;
    private String createdBy;
}
