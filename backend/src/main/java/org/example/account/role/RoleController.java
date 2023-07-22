package org.example.account.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {

	@Autowired
	RoleServices services; 
	
	@PostMapping("/")
	public ResponseEntity<Role> createRole(@RequestBody RoleDto roleDto) {
		Role response = services.createRole(roleDto);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping("/")
	public ResponseEntity<List<RoleDto>> getAllRoles() {
		List<RoleDto> response = services.getAllRoles();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	
}
