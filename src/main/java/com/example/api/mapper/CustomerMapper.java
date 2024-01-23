package com.example.api.mapper;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer dtoToEntity(CustomerDto dto);

    CustomerDto entityToDto(Customer entity);

    List<CustomerDto> listEntityToListDto(List<Customer> collectionCustomer);

    List<Customer> listDtoToListEntity(List<CustomerDto> collectionDto);

}
