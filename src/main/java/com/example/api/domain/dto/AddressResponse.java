package com.example.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private Long id;

    private Long customerId;

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
