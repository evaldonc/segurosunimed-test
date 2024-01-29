package com.example.api.web.rest.impl;

import com.example.api.domain.dto.CustomerRequest;
import com.example.api.domain.dto.CustomerResponse;
import com.example.api.exception.BusinessException;
import com.example.api.exception.CustomerNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CustomerControllerInterface {

    @Operation(summary = "Find all customer", description = "Find all customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerNotFoundException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessException.class))})})
    @Parameter(name = "name", description = "Customer name")
    @Parameter(name = "email", description = "Customer email")
    @Parameter(name = "gender", description = "Customer gender")
    @Parameter(name = "city", description = "Customer city")
    @Parameter(name = "state", description = "Customer state")
    @Parameter(name = "pageable", description = "Pegeable info", example = "{\n" +
            "  \"page\": 0,\n" +
            "  \"size\": 20,\n" +
            "  \"sort\": [\n" +
            "    \"name\"\n" +
            "  ]\n" +
            "}")
    ResponseEntity<Page<CustomerResponse>> findAll(String name, String email, String gender,
                                                   String city, String state, Pageable pageable);

    @Operation(summary = "Find customer by id", description = "Find customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerNotFoundException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessException.class))})})
    @Parameter(name = "id", description = "Customer id")
    ResponseEntity<CustomerResponse> findById(@PathVariable Long id);

    @Operation(summary = "Save customer", description = "Save customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Long.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerNotFoundException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessException.class))})})
    ResponseEntity<Long> save(@RequestBody CustomerRequest request);

    @Operation(summary = "Update customer by id", description = "Update customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Long.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerNotFoundException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessException.class))})})
    @Parameter(name = "id", description = "Customer id")
    ResponseEntity<Long> update(@RequestBody @Valid CustomerRequest request, @PathVariable Long id);

    @Operation(summary = "Delete customer by id", description = "Delete customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerNotFoundException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessException.class))})})
    @Parameter(name = "id", description = "Customer id")
    ResponseEntity delete(@PathVariable Long id);

}
