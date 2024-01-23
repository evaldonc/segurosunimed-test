package com.example.api.web.rest;

import com.example.api.domain.dto.CustomerDto;
import com.example.api.exception.UnimedRestException;
import com.example.api.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@Operation(description = "Find all customer")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = CustomerDto.class))}),
			@ApiResponse(responseCode = "404", description = "Not Found",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = UnimedRestException.class))}),
			@ApiResponse(responseCode = "500", description = "Internal Server Error",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = UnimedRestException.class))})})
	@GetMapping
	public ResponseEntity<List<CustomerDto>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@Operation(description = "Find customer by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = CustomerDto.class))}),
			@ApiResponse(responseCode = "404", description = "Not Found",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = UnimedRestException.class))}),
			@ApiResponse(responseCode = "500", description = "Internal Server Error",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = UnimedRestException.class))})})
	@Parameter(name = "name", description = "Customer Name")
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
		return new ResponseEntity<>(service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")), HttpStatus.OK);
	}

	@Operation(description = "Find customer by name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = CustomerDto.class))}),
			@ApiResponse(responseCode = "404", description = "Not Found",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = UnimedRestException.class))}),
			@ApiResponse(responseCode = "500", description = "Internal Server Error",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = UnimedRestException.class))})})
	@Parameter(name = "name", description = "Customer Name")
	@GetMapping("/name/{name}")
	public ResponseEntity<List<CustomerDto>> findByName(@PathVariable String name) {
		return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);

	}

	@Operation(description = "Find customer by email")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = CustomerDto.class))}),
			@ApiResponse(responseCode = "404", description = "Not Found",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = UnimedRestException.class))}),
			@ApiResponse(responseCode = "500", description = "Internal Server Error",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = UnimedRestException.class))})})
	@Parameter(name = "email", description = "Customer email")
	@GetMapping("/email/{email}")
	public ResponseEntity<CustomerDto> findByEmail(@PathVariable String email) {
		return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
	}

	@Operation(description = "Find customer by gender")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ok",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = CustomerDto.class))}),
			@ApiResponse(responseCode = "404", description = "Not Found",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = UnimedRestException.class))}),
			@ApiResponse(responseCode = "500", description = "Internal Server Error",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = UnimedRestException.class))})})
	@Parameter(name = "gender", description = "Customer gender")
	@GetMapping("/gender/{gender}")
	public ResponseEntity<List<CustomerDto>> findByGender(@PathVariable String gender) {
		return new ResponseEntity<>(service.findByGender(gender), HttpStatus.OK);
	}

}
