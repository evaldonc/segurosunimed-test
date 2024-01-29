package com.example.api.service.impl;

import com.example.api.domain.Address;
import com.example.api.domain.dto.AddressRequest;
import com.example.api.domain.dto.AddressResponse;
import com.example.api.domain.dto.AddressViaCep;
import com.example.api.exception.BusinessException;
import com.example.api.mapper.AddressMapper;
import com.example.api.repository.AddressRepository;
import com.example.api.web.rest.ViaCepClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    @Mock
    private AddressRepository repository;

    @Mock
    private AddressMapper mapper;

    @Mock
    private ViaCepClient viaCepClient;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findAddressByCustomerId() {
        // Mocking repository behavior
        when(repository.findByCustomerId(anyLong())).thenReturn(Arrays.asList(createMockAddress()));

        // Mocking mapper behavior
        when(mapper.listEntityToListResponse(anyList())).thenReturn(Arrays.asList(createMockAddressResponse()));

        // Call the service method
        List<AddressResponse> result = addressService.findAddressByCustomerId(1L);

        // Assertions
        assertEquals(1, result.size());
        assertEquals(createMockAddressResponse(), result.get(0));
    }

    @Test
    public void save() {
        when(mapper.requestToEntity(any())).thenReturn(createMockAddress());
        when(repository.save(any())).thenReturn(createMockAddress());

        Long result = addressService.save(createMockAddressRequest());

        assertEquals(createMockAddress().getId(), result);
    }

    @Test
    public void save_only_cep() {
        when(mapper.requestToEntity(any())).thenReturn(createMockAddress());
        when(repository.save(any())).thenReturn(createMockAddress());
        when(viaCepClient.addressViaCep(anyString())).thenReturn(createMockAddressViaCep());

        Long result = addressService.save(AddressRequest.builder().cep("01001000").build());

        assertEquals(createMockAddress().getId(), result);
    }

    @Test(expected = BusinessException.class)
    public void save_error() {
        Long result = addressService.save(null);
    }

    @Test
    public void findAddressByCep() {
        // Mocking ViaCepClient behavior
        when(viaCepClient.addressViaCep(anyString())).thenReturn(createMockAddressViaCep());

        // Call the service method
        AddressViaCep result = addressService.findAddressByCep("12345678");

        // Assertions
        assertEquals(createMockAddressViaCep(), result);
    }

    // Helper methods to create mock objects for testing

    private Address createMockAddress() {
        return Address.builder()
                .id(1L)
                .customerId(1L)
                .cep("12345678")
                .logradouro("Mock Street")
                .complemento("Apt 1")
                .bairro("Mock Neighborhood")
                .localidade("Mock City")
                .uf("MO")
                .ibge(123456L)
                .gia(789012L)
                .ddd(42L)
                .siafi(987654L)
                .build();
    }

    private AddressRequest createMockAddressRequest() {
        return AddressRequest.builder()
                .customerId(1L)
                .cep("12345678")
                .logradouro("Mock Street")
                .complemento("Apt 1")
                .bairro("Mock Neighborhood")
                .localidade("Mock City")
                .uf("MO")
                .build();
    }

    private AddressResponse createMockAddressResponse() {
        return AddressResponse.builder()
                .id(1L)
                .customerId(1L)
                .cep("12345678")
                .logradouro("Mock Street")
                .complemento("Apt 1")
                .bairro("Mock Neighborhood")
                .localidade("Mock City")
                .uf("MO")
                .ibge(123456L)
                .gia(789012L)
                .ddd(42L)
                .siafi(987654L)
                .build();
    }

    private AddressViaCep createMockAddressViaCep() {
        return AddressViaCep.builder()
                .cep("12345678")
                .logradouro("Mock Street")
                .complemento("Apt 1")
                .bairro("Mock Neighborhood")
                .localidade("Mock City")
                .uf("MO")
                .ibge(123456L)
                .gia(789012L)
                .ddd(42L)
                .siafi(987654L)
                .build();
    }
}
