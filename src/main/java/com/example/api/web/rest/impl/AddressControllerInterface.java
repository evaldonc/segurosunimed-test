package com.example.api.web.rest.impl;

import com.example.api.domain.dto.AddressRequest;
import com.example.api.domain.dto.AddressResponse;
import com.example.api.domain.dto.AddressViaCep;
import com.example.api.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AddressControllerInterface {

    @Operation(summary = "Find addresses by customer id", description = "Find addresses by customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessException.class))})})
    @Parameter(name = "customerId", description = "Customer id")
    ResponseEntity<List<AddressResponse>> findAddressByCustomerId(@PathVariable Long customerId);

    @Operation(summary = "Save customer's address", description = "Save customer's address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Long.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessException.class))})})
    ResponseEntity<Long> save(@RequestBody AddressRequest request);

    @Operation(summary = "Find addresses by cep",
            description = "The service return must be used to fill in the new request with the additional fields filled in. Ex. street number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressViaCep.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessException.class))})})
    @Parameter(name = "cep", description = "Código de endereçamento postal")
    ResponseEntity<AddressViaCep> findAddressByCep(@PathVariable String cep);


}
