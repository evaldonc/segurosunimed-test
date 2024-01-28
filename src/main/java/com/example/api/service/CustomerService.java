package com.example.api.service;

import com.example.api.domain.dto.CustomerRequest;
import com.example.api.domain.dto.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {

    Page<CustomerResponse> findAll(String nome, String email, String gender, String city, String state, Pageable pageable);

    Optional<CustomerResponse> findById(Long id);

    Long save(CustomerRequest request);

    Long update(CustomerRequest request, Long id);

    String delete(Long id);

}
