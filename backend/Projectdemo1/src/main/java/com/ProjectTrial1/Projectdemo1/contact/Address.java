package com.ProjectTrial1.Projectdemo1.contact;

import com.ProjectTrial1.Projectdemo1.hirebarber.servicezone.ServiceZone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "address_id", unique=true)
	private String addressId;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "state")
	private String state;
	@Column(name = "city")
	private String city;
	@Column(name = "house")
	private String house;
	@Column(name = "locality")
	private String locality;
	@Column(name = "pin_code")
	private String pinCode;

//	@OneToOne
//	private ServiceZone serviceZoneId;
//
//	@Column(name = "is_active")
//	private boolean isActive = true;
	
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
	
}
