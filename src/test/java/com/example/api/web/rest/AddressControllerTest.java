package com.example.api.web.rest;

import com.example.api.domain.dto.AddressRequest;
import com.example.api.domain.dto.AddressResponse;
import com.example.api.domain.dto.AddressViaCep;
import com.example.api.service.AddressService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findAddressByCustomerId() {
        List<AddressResponse> addressList = new ArrayList<>();
        when(addressService.findAddressByCustomerId(anyLong())).thenReturn(addressList);

        ResponseEntity<List<AddressResponse>> result = addressController.findAddressByCustomerId(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(addressList, result.getBody());
    }

    @Test
    public void save() {
        Long addressId = 1L;
        when(addressService.save(any(AddressRequest.class))).thenReturn(addressId);

        ResponseEntity<Long> result = addressController.save(mock(AddressRequest.class));

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(addressId, result.getBody());
    }

    @Test
    public void findAddressByCep() {
        AddressViaCep addressViaCep = new AddressViaCep();
        when(addressService.findAddressByCep(anyString())).thenReturn(addressViaCep);

        ResponseEntity<AddressViaCep> result = addressController.findAddressByCep("12345678");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(addressViaCep, result.getBody());
    }

}
