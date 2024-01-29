package com.example.api.service.impl;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerRequest;
import com.example.api.domain.dto.CustomerResponse;
import com.example.api.exception.CustomerNotFoundException;
import com.example.api.mapper.CustomerMapper;
import com.example.api.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerRequest validCustomerRequest;
    private Customer validCustomer;
    private CustomerResponse validCustomerResponse;
    private Pageable pageable;

    @Before
    public void setUp() throws Exception {
        validCustomerRequest = CustomerRequest.builder()
                .name("Alan Prost")
                .email("prost@example.com")
                .gender("M")
                .build();

        validCustomer = Customer.builder()
                .id(1L)
                .name("Alan Prost")
                .email("prost@example.com")
                .gender("M")
                .build();

        validCustomerResponse = CustomerResponse.builder()
                .id(1L)
                .name("Alan Prost")
                .email("prost@example.com")
                .gender("M")
                .build();

        pageable = mock(Pageable.class);
    }

    @Test
    public void findAll() {
        when(repository.findAll((Specification) any(), eq(pageable)))
                .thenReturn(new PageImpl<>(List.of(validCustomer)));

        when(mapper.listEntityToListResponse(List.of(validCustomer)))
                .thenReturn(List.of(validCustomerResponse));

        Page<CustomerResponse> result = customerService.findAll(null, null, null, null, null, pageable);

        assertEquals(1, result.getContent().size());
        assertEquals(validCustomerResponse, result.getContent().get(0));
    }

    @Test
    public void findById_CustomerExists() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(validCustomer));

        when(mapper.entityToResponse(validCustomer)).thenReturn(validCustomerResponse);

        Optional<CustomerResponse> result = customerService.findById(1L);

        assertEquals(Optional.of(validCustomerResponse), result);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void findById_CustomerNotExists() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        customerService.findById(1L);
    }

    @Test
    public void update_CustomerExists() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(validCustomer));
        when(repository.save(any())).thenReturn(validCustomer);

        Long result = customerService.update(validCustomerRequest, 1L);

        assertEquals(validCustomer.getId(), result);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void update_CustomerNotExists() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        customerService.update(validCustomerRequest, 1L);
    }

    @Test
    public void save() {
        when(mapper.requestToEntity(validCustomerRequest)).thenReturn(validCustomer);
        when(repository.save(validCustomer)).thenReturn(validCustomer);

        Long result = customerService.save(validCustomerRequest);

        assertEquals(validCustomer.getId(), result);
    }

    @Test
    public void delete_CustomerExists() {
        when(repository.existsById(anyLong())).thenReturn(true);

        customerService.delete(1L);

        verify(repository).deleteById(1L);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void delete_CustomerNotExists() {
        when(repository.existsById(anyLong())).thenReturn(false);

        customerService.delete(1L);
    }
}
