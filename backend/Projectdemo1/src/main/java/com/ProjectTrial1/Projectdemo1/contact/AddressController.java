package com.ProjectTrial1.Projectdemo1.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
public class AddressController {
	@Autowired
	AddressServices services;

	@PostMapping("/")
	public ResponseEntity<Address> addAddress(@RequestBody AddressDto addressDto) throws Exception {
		Address response = services.addAddress(addressDto);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/")
	public ResponseEntity<List<AddressDto>> getAllAddresses() {
		List<AddressDto> response = services.getAllAddresses();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/by-user/{userId}")
	public ResponseEntity<List<AddressDto>> getAddressByUserId(@PathVariable String userId) {
		List<AddressDto> response = services.getAddressByUserId(userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{addressId}")
	public ResponseEntity<AddressDto> getAddressByAddressId(@PathVariable String addressId) {
		AddressDto response = services.getAddressByAddressId(addressId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
