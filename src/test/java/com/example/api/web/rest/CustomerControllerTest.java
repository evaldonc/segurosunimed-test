package com.example.api.web.rest;

import com.example.api.domain.dto.CustomerRequest;
import com.example.api.domain.dto.CustomerResponse;
import com.example.api.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    private CustomerServiceImpl service;

    @InjectMocks
    private CustomerController controller;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findAll() {
        // Mocking service behavior
        Page<CustomerResponse> customerPage = new PageImpl<>(new ArrayList<>());
        Mockito.lenient().when(service.findAll(anyString(), anyString(), anyString(), anyString(), anyString(), any(Pageable.class)))
                .thenReturn(customerPage);

        // Call the controller method
        ResponseEntity<Page<CustomerResponse>> result = controller.findAll(null, null, null, null, null, mock(Pageable.class));

        // Assertions
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void findById_CustomerExists() {
        // Mocking service behavior
        CustomerResponse customerResponse = new CustomerResponse();
        when(service.findById(anyLong())).thenReturn(Optional.of(customerResponse));

        // Call the controller method
        ResponseEntity<CustomerResponse> result = controller.findById(1L);

        // Assertions
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(customerResponse, result.getBody());
    }

    @Test(expected = ResponseStatusException.class)
    public void findById_CustomerNotExists() {
        // Mocking service behavior
        when(service.findById(anyLong())).thenReturn(Optional.empty());

        // Call the controller method
        controller.findById(1L);  // This should throw ResponseStatusException
    }

    @Test
    public void save() {
        // Mocking service behavior
        Long customerId = 1L;
        when(service.save(any(CustomerRequest.class))).thenReturn(customerId);

        // Call the controller method
        ResponseEntity<Long> result = controller.save(mock(CustomerRequest.class));

        // Assertions
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(customerId, result.getBody());
    }

    @Test
    public void update() {
        // Mocking service behavior
        Long customerId = 1L;
        when(service.update(any(CustomerRequest.class), anyLong())).thenReturn(customerId);

        // Call the controller method
        ResponseEntity<Long> result = controller.update(mock(CustomerRequest.class), 1L);

        // Assertions
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(customerId, result.getBody());
    }

    @Test
    public void delete() {
        // Mocking service behavior
        doNothing().when(service).delete(anyLong());

        // Call the controller method
        ResponseEntity result = controller.delete(1L);

        // Assertions
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    private CustomerResponse buildCustomer() {
        return CustomerResponse.builder()
                .id(1L)
                .name("Nick Lauda")
                .email("lauda@example.com")
                .gender("M")
                .build();
    }
}
