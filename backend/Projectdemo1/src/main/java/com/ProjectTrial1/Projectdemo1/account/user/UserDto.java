package com.ProjectTrial1.Projectdemo1.account.user;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	private String userName;
	private String userPassWord;
	private String gender;
	private LocalDate dob;
	private String phoneNo;
	private String altPhoneNo;
	private String emailId;
}
