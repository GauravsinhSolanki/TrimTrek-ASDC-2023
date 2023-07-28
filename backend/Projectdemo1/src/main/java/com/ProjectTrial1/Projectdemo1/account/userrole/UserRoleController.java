package com.ProjectTrial1.Projectdemo1.account.userrole;

import com.ProjectTrial1.Projectdemo1.account.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/user-role")
@CrossOrigin("*")
public class UserRoleController {

	@Autowired
	UserRoleServices services;

	@PostMapping("/")
	public ResponseEntity<List<UserRole>> createUserRole(@RequestBody UserRoleDto userRoleDto) {
		List<UserRole> response = services.createUserRole(userRoleDto);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/")
	public ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
		List<UserRoleDto> response = services.getAllUserRoles();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/customer/barber")
	public ResponseEntity<List<UserDto>> getCustomerAndBarber() {
		List<UserDto> response = services.getCustomerAndBarber();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/user-role/{userId}")
	public ResponseEntity<UserRoleDto> getUserRoleByUserId(@PathVariable String userId) {
		UserRoleDto response = services.getUserRoleByUserId(userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@GetMapping("/hire-barber/barbers")
	public ResponseEntity<List<UserProfileDto>> getAllBarberUserRole() {
		List<UserProfileDto> response = services.getAllBarberUserRole();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@GetMapping("/hire-barber/admins")
	public ResponseEntity<List<UserProfileDto>> getAllAdminRole() {
		List<UserProfileDto> response = services.getAllAdminRole();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/hire-gardener/customers")
	public ResponseEntity<List<UserProfileDto>> getAllCustomerRole() {
		List<UserProfileDto> response = services.getAllCustomerRole();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
