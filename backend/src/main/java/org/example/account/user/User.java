package org.example.account.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "user_id", unique = true)
	private String userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_password")
	private String userPassWord;

	@Column(name = "gender")
	private String gender;

	@Column(name = "dob", columnDefinition = "Date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@Column(name = "phone_no")
	private String phoneNo;

	@Column(name = "alt_phone_no")
	private String altPhoneNo;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "speciality")
	private String speciality;

	@Column(name = "is_active")
	private boolean isActive = true;

	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

}
