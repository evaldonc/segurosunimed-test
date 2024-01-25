package com.example.api.repository;

import com.example.api.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

//	List<Customer> findAllByOrderByNameAsc(Specification spec, Pageable pageable);
	Page<Customer> findAll(Specification spec, Pageable pageable);

	List<Customer> findByNameStartingWith(String name);

	Customer findByEmail(String email);

	List<Customer> findByGender(String gender);

}
