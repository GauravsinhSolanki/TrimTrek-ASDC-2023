package com.ProjectTrial1.Projectdemo1.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
	private String addressId;
	private String userId;
	private String state;
	private String city;
	private String house;
	private String locality;
	private String pinCode;
//	private String serviceZoneId;
	private String createdBy;
}
