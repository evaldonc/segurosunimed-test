package com.example.api.service;

import com.example.api.domain.dto.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Page<CustomerDto> findAll(String nome, String email, String gender, Pageable pageable);

    Optional<CustomerDto> findById(Long id);

    List<CustomerDto> findByName(String name);

    CustomerDto findByEmail(String email);

    List<CustomerDto> findByGender(String gender);

}
