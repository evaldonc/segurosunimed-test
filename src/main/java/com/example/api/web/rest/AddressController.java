package com.example.api.web.rest;

import com.example.api.domain.Address;
import com.example.api.domain.dto.AddressRequest;
import com.example.api.domain.dto.AddressResponse;
import com.example.api.domain.dto.AddressViaCep;
import com.example.api.service.AddressService;
import com.example.api.web.rest.impl.AddressControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController implements AddressControllerInterface {

    private final AddressService addressService;

    @Override
    @GetMapping("/customerId/{customerId}")
    public ResponseEntity<List<AddressResponse>> findAddressByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(addressService.findAddressByCustomerId(customerId));
    }

    @Override
    @PostMapping
    public ResponseEntity<Long> save(@RequestBody @Valid AddressRequest request) {
        return ResponseEntity.ok(addressService.save(request));
    }

    @Override
    @GetMapping("/cep/{cep}")
    public ResponseEntity<AddressViaCep> findAddressByCep(@PathVariable String cep) {
        return ResponseEntity.ok(addressService.findAddressByCep(cep));
    }

}
