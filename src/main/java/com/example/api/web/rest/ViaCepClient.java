package com.example.api.web.rest;

import com.example.api.domain.dto.AddressViaCep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

    @GetMapping(value = "/{cep}/json", produces = "application/json")
    AddressViaCep addressViaCep(@PathVariable String cep);

}
