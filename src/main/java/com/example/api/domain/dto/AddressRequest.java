package com.example.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private Long customerId;

    @Pattern(regexp = "^([\\d]{8})", message = "CEP mandatory. Ex. 12345678.")
    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    private String localidade;

    private String uf;

    private Long ibge;

    private Long gia;

    private Long ddd;

    private Long siafi;

}
