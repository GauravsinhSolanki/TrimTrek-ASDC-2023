package com.ProjectTrial1.Projectdemo1.account.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserServices services;

	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
		User response = services.createUser(userDto);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> response = services.getAllUsers();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@GetMapping("/by-email/{emailId}")
	public ResponseEntity<UserDto> getUserByEmailId(@PathVariable String emailId) {
		UserDto response = services.getUserByEmailId(emailId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/user-id/{emailId}")
	public ResponseEntity<String> getUserIdByEmailId(@PathVariable String emailId) {
		String response = services.getUserIdByEmailId(emailId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/email-id-by-user-id/{userId}")
	public ResponseEntity<String> getEmailIdByUserId(@PathVariable String userId) {
		String response = services.getEmailIdByUserId(userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/sign-in-by-/{emailId}/{userPassWord}")
	public ResponseEntity<String> signIn(@PathVariable String emailId, @PathVariable String userPassWord) {
		return  services.signIn(emailId,userPassWord);
	}


	@PutMapping("/barber-profile-create")
	public ResponseEntity<User> barberProfile(@RequestBody BarberDto barberDto) {
		User response = services.createBarberProfile(barberDto);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/get-barber-profile-by-email/{emailId}")
	public ResponseEntity<BarberProfileDto> getBarberProfileByEmailId(@PathVariable String emailId) {
		BarberProfileDto response = services.getBarberProfileByEmailId(emailId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
