package com.example.api.domain.dto;


import lombok.*;

import javax.validation.constraints.Email;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NonNull
    private String name;

    @Email
    private String email;

    @NonNull
    private String gender;

}
