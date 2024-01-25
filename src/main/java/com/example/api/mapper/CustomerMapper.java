package com.example.api.mapper;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer dtoToEntity(CustomerResponse dto);

    CustomerResponse entityToDto(Customer entity);

    List<CustomerResponse> listEntityToListDto(List<Customer> collectionCustomer);

    List<Customer> listDtoToListEntity(List<CustomerResponse> collectionDto);

}
