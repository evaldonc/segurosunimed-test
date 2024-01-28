package com.example.api.service.impl;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerRequest;
import com.example.api.domain.dto.CustomerResponse;
import com.example.api.exception.CustomerNotFoundException;
import com.example.api.mapper.CustomerMapper;
import com.example.api.repository.CustomerRepository;
import com.example.api.repository.spec.CustomerSpecification;
import com.example.api.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository repository;
	private final CustomerMapper mapper;

	@Override
	public Page<CustomerResponse> findAll(String name, String email, String gender, String city, String state, Pageable pageable) {
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
		if (Objects.nonNull(city)) {
			spec = spec.and(CustomerSpecification.city(city));
		}
		if (Objects.nonNull(state)) {
			spec = spec.and(CustomerSpecification.uf(state));
		}

		return convertCustomerPageInCustomerResponsePage(pageable, spec);
	}

	@Override
	public Optional<CustomerResponse> findById(Long id) {
 		return repository.findById(id).map(mapper::entityToResponse);
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
				.orElseThrow(CustomerNotFoundException::new);
        return null;
    }

	@Override
	public Long save(CustomerRequest request) {
		Customer customer = mapper.requestToEntity(request);
		return repository.save(customer).getId();
	}


	private Page<CustomerResponse> convertCustomerPageInCustomerResponsePage(Pageable pageable, Specification<Customer> spec) {
		Page<Customer> customerPage = repository.findAll(spec, pageable);
		List<Customer> customerList = customerPage.getContent();
		List<CustomerResponse> customerResponseList = mapper.listEntityToListResponse(customerList);
		return new PageImpl<>(customerResponseList, pageable, customerResponseList.size());
	}

	@Override
	public String delete(Long id) {
		try {
			repository.deleteById(id);
			return "Customer deleted";
		} catch (Exception e) {
			throw new CustomerNotFoundException();
		}
	}

}
