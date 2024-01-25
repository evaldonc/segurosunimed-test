package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerDto;
import com.example.api.mapper.CustomerMapper;
import com.example.api.repository.CustomerRepository;
import com.example.api.repository.CustomerSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

	private final CustomerRepository repository;
	private final CustomerMapper mapper;

	public Page<CustomerDto> findAll(String name, String email, String gender, Pageable pageable) {
		Specification<Customer> spec = Specification.where(null);
		if (Objects.nonNull(name)) {
			spec = spec.and(CustomerSpecification.name(name));
		}
		if (Objects.nonNull(email)) {
			spec = spec.and(CustomerSpecification.email(email));
		}
		if (Objects.nonNull(gender)) {
			spec = spec.and(CustomerSpecification.gender(gender));
		}

		return convertCustomerPageInCustomerDtoPage(pageable, spec);
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

	private Page<CustomerDto> convertCustomerPageInCustomerDtoPage(Pageable pageable, Specification<Customer> spec) {
		Page<Customer> customerPage = repository.findAll(spec, pageable);
		List<Customer> customerList = customerPage.getContent();
		List<CustomerDto> customerDtoList = mapper.listEntityToListDto(customerList);
		return new PageImpl<>(customerDtoList, pageable, customerDtoList.size());
	}

}
