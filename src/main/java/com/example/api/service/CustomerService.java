package com.example.api.service;

import com.example.api.domain.dto.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Page<CustomerResponse> findAll(String nome, String email, String gender, Pageable pageable);

    Optional<CustomerResponse> findById(Long id);

    List<CustomerResponse> findByName(String name);

    CustomerResponse findByEmail(String email);

    List<CustomerResponse> findByGender(String gender);

}
