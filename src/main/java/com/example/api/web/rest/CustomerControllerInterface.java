package com.example.api.web.rest;

import com.example.api.domain.dto.CustomerDto;
import com.example.api.exception.UnimedRestException;
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

import java.util.List;

public interface CustomerControllerInterface {

    @Operation(description = "Find all customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnimedRestException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnimedRestException.class))})})
    ResponseEntity<Page<CustomerDto>> findAll(String nome, String email, String gender, Pageable pageable);

    @Operation(description = "Find customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnimedRestException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnimedRestException.class))})})
    @Parameter(name = "name", description = "Customer Name")
    ResponseEntity<CustomerDto> findById(@PathVariable Long id);

    @Operation(description = "Find customer by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnimedRestException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnimedRestException.class))})})
    @Parameter(name = "name", description = "Customer Name")
    ResponseEntity<List<CustomerDto>> findByName(@PathVariable String name);

    @Operation(description = "Find customer by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnimedRestException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnimedRestException.class))})})
    @Parameter(name = "email", description = "Customer email")
    ResponseEntity<CustomerDto> findByEmail(@PathVariable String email);

    @Operation(description = "Find customer by gender")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnimedRestException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnimedRestException.class))})})
    @Parameter(name = "gender", description = "Customer gender")
    ResponseEntity<List<CustomerDto>> findByGender(@PathVariable String gender);

}
