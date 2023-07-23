package com.ProjectTrial1.Projectdemo1.account.user;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BarberProfileDto {
    private String userName;
    private String gender;
    private LocalDate dob;
    private String phoneNo;
    private String altPhoneNo;
    private String emailId;
    private String speciality;
}
