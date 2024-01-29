package com.example.api.service.impl;

import com.example.api.domain.Address;
import com.example.api.domain.dto.AddressRequest;
import com.example.api.domain.dto.AddressResponse;
import com.example.api.domain.dto.AddressViaCep;
import com.example.api.exception.BusinessException;
import com.example.api.mapper.AddressMapper;
import com.example.api.repository.AddressRepository;
import com.example.api.service.AddressService;
import com.example.api.web.rest.ViaCepClient;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private AddressRepository repository;

    private AddressMapper mapper;

    private ViaCepClient viaCepClient;

    @Override
    public List<AddressResponse> findAddressByCustomerId(Long customerId) {
        return mapper.listEntityToListResponse(repository.findByCustomerId(customerId));
    }

    @Override
    public Long save(AddressRequest request) {
        if (Objects.isNull(request) || isAllNull(request)) {
            throw new BusinessException("Address is mandatory");
        }

        if (isOnlyCepNotNull(request)) {
            AddressViaCep addressViaCep = findAddressByCep(request.getCep());
            request.setLogradouro(addressViaCep.getLogradouro());
            request.setComplemento(addressViaCep.getComplemento());
            request.setBairro(addressViaCep.getBairro());
            request.setLocalidade(addressViaCep.getLocalidade());
            request.setUf(addressViaCep.getUf());
            request.setIbge(addressViaCep.getIbge());
            request.setGia(addressViaCep.getGia());
            request.setDdd(addressViaCep.getDdd());
            request.setSiafi(addressViaCep.getSiafi());
        }

        Address entity = mapper.requestToEntity(request);
        return repository.save(entity).getId();
    }

    @Override
    public AddressViaCep findAddressByCep(String cep) {
        return viaCepClient.addressViaCep(cep);
    }



    private boolean isAllNull(AddressRequest request) {
        return ObjectUtils.allNull(request.getLogradouro(), request.getComplemento(), request.getBairro(),
                request.getLocalidade(), request.getUf(), request.getCep());
    }

    private boolean isOnlyCepNotNull(AddressRequest request) {
        return ObjectUtils.allNull(request.getLogradouro(), request.getComplemento(), request.getBairro(),
                request.getLocalidade(), request.getUf()) && Objects.nonNull(request.getCep());
    }

}
