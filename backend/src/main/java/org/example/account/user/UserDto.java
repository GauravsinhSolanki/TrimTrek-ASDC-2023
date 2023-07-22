package org.example.account.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String userName;
	private String passWord;
	private String gender;
	private LocalDate dob;
	private String phoneNo;
	private String altPhoneNo;
	private String emailId;
}
