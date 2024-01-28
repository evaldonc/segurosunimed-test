package com.example.api.service;

import com.example.api.domain.dto.AddressRequest;
import com.example.api.domain.dto.AddressResponse;
import com.example.api.domain.dto.AddressViaCep;

import java.util.List;

public interface AddressService {

    List<AddressResponse> findAddressByCustomerId(Long customerId);

    Long save(AddressRequest address);

    AddressViaCep findAddressByCep(String cep);

}
