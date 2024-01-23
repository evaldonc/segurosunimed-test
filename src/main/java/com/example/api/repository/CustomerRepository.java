package com.example.api.repository;

import com.example.api.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findAllByOrderByNameAsc();

	List<Customer> findByNameStartingWith(String name);

	Customer findByEmail(String email);

	List<Customer> findByGender(String gender);

}
