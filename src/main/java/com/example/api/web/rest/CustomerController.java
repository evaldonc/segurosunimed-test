package com.example.api.web.rest;

import com.example.api.domain.dto.CustomerDto;
import com.example.api.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController implements CustomerControllerInterface{

	private final CustomerServiceImpl service;

	@Autowired
	public CustomerController(CustomerServiceImpl service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Page<CustomerDto>> findAll(@RequestParam(required = false) String name, @RequestParam(required = false) String email, @RequestParam(required = false) String gender, Pageable pageable) {
		return new ResponseEntity<>(service.findAll(name, email, gender, pageable), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
		return new ResponseEntity<>(service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")), HttpStatus.OK);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<CustomerDto>> findByName(@PathVariable String name) {
		return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);

	}

	@GetMapping("/email/{email}")
	public ResponseEntity<CustomerDto> findByEmail(@PathVariable String email) {
		return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
	}

	@GetMapping("/gender/{gender}")
	public ResponseEntity<List<CustomerDto>> findByGender(@PathVariable String gender) {
		return new ResponseEntity<>(service.findByGender(gender), HttpStatus.OK);
	}

}
