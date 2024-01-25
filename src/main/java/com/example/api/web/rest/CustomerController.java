package com.example.api.web.rest;

import com.example.api.domain.dto.CustomerRequest;
import com.example.api.domain.dto.CustomerResponse;
import com.example.api.service.impl.CustomerServiceImpl;
import com.example.api.web.rest.impl.CustomerControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController implements CustomerControllerInterface {

	private final CustomerServiceImpl service;

	@Autowired
	public CustomerController(CustomerServiceImpl service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Page<CustomerResponse>> findAll(@RequestParam(required = false) String name, @RequestParam(required = false) String email, @RequestParam(required = false) String gender, Pageable pageable) {
		return new ResponseEntity<>(service.findAll(name, email, gender, pageable), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponse> findById(@PathVariable Long id) {
		return new ResponseEntity<>(service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Long> save(@RequestBody @Valid CustomerRequest request) {
		return ResponseEntity.ok(service.save(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Long> update(@RequestBody @Valid CustomerRequest request, @PathVariable Long id) {
		return ResponseEntity.ok(service.update(request, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		return ResponseEntity.ok(service.delete(id));
	}

}
