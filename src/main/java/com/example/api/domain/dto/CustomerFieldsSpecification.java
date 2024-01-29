package com.example.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFieldsSpecification {

    private String name;

    private String email;

    private String gender;

    private String city;

    private String state;

}
