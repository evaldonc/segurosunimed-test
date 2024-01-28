package com.example.api.mapper;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerRequest;
import com.example.api.domain.dto.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer requestToEntity(CustomerRequest request);

    CustomerResponse entityToResponse(Customer entity);

    List<CustomerResponse> listEntityToListResponse(List<Customer> collectionCustomer);

    List<Customer> listRequestToListEntity(List<CustomerRequest> collectionRequest);

}
