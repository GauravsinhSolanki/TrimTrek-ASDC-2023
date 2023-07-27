package com.ProjectTrial1.Projectdemo1.account.userrole;

import com.ProjectTrial1.Projectdemo1.account.role.Role;
import com.ProjectTrial1.Projectdemo1.account.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_role")
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userId;
	private int roleId;
	private boolean isActive = true;
	private String createdBy;
	private LocalDateTime createdOn;
	private String updatedBy;
	private LocalDateTime updatedOn;

}
