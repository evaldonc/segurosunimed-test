package com.example.api.mapper;

import com.example.api.domain.Address;
import com.example.api.domain.dto.AddressRequest;
import com.example.api.domain.dto.AddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    Address requestToEntity(AddressRequest request);

    AddressResponse entityToResponse(Address entity);

    List<AddressResponse> listEntityToListResponse(List<Address> collectionEntity);

    List<Address> listRequestToListEntity(List<AddressRequest> collectionRequest);

}
