package com.example.api.service.impl;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerRequest;
import com.example.api.domain.dto.CustomerResponse;
import com.example.api.exception.BusinessException;
import com.example.api.mapper.CustomerMapper;
import com.example.api.repository.CustomerRepository;
import com.example.api.repository.CustomerSpecification;
import com.example.api.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository repository;
	private final CustomerMapper mapper;

	@Override
	public Page<CustomerResponse> findAll(String name, String email, String gender, Pageable pageable) {
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

		return convertCustomerPageInCustomerResponsePage(pageable, spec);
	}

	@Override
	public Optional<CustomerResponse> findById(Long id) {
 		return repository.findById(id).map(mapper::entityToDto);
	}

	@Override
	public List<CustomerResponse> findByName(String name) {
		return mapper.listEntityToListDto(repository.findByNameStartingWith(name));
	}

	@Override
	public CustomerResponse findByEmail(String email) {
		return mapper.entityToDto(repository.findByEmail(email));
	}

	@Override
	public List<CustomerResponse> findByGender(String gender) {
		return mapper.listEntityToListDto(repository.findByGender(gender));
	}

	@Override
	public Long update(CustomerRequest request, Long id) {
		repository.findById(id)
				.map(customer -> {
					customer.setName(request.getName());
					customer.setEmail(request.getEmail());
					customer.setGender(request.getGender());
					return repository.save(customer).getId();
				})
				.orElseThrow( () -> new BusinessException("Customer not found with id=" + id.toString()) );
        return null;
    }

	@Override
	public Long save(CustomerRequest request) {
		Customer customer = Customer.builder()
				.name(request.getName())
				.email(request.getEmail())
				.gender(request.getGender())
				.build();
		return repository.save(customer).getId();
	}


	private Page<CustomerResponse> convertCustomerPageInCustomerResponsePage(Pageable pageable, Specification<Customer> spec) {
		Page<Customer> customerPage = repository.findAll(spec, pageable);
		List<Customer> customerList = customerPage.getContent();
		List<CustomerResponse> customerResponseList = mapper.listEntityToListDto(customerList);
		return new PageImpl<>(customerResponseList, pageable, customerResponseList.size());
	}

	@Override
	public String delete(Long id) {
		try {
			repository.deleteById(id);
			return "Customer deleted";
		} catch (Exception e) {
			return "Customer not deleted";
		}
	}

}
