package com.example.api.service;

import com.example.api.domain.dto.CustomerDto;
import com.example.api.mapper.CustomerMapper;
import com.example.api.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

	private final CustomerRepository repository;
	private final CustomerMapper mapper;

	public List<CustomerDto> findAll() {
		return mapper.listEntityToListDto(repository.findAllByOrderByNameAsc());
	}

	public Optional<CustomerDto> findById(Long id) {
 		return repository.findById(id).map(mapper::entityToDto);
	}

	public List<CustomerDto> findByName(String name) {
		return mapper.listEntityToListDto(repository.findByNameStartingWith(name));
	}

	public CustomerDto findByEmail(String email) {
		return mapper.entityToDto(repository.findByEmail(email));
	}

	public List<CustomerDto> findByGender(String gender) {
		return mapper.listEntityToListDto(repository.findByGender(gender));
	}

}
