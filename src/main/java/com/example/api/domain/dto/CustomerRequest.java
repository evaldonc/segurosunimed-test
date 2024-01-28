package com.example.api.domain.dto;


import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @Size(min=2, max=100)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email
    @Size(max=256)
    @NotBlank(message = "e-Mail is mandatory")
    private String email;

    @NotBlank
    @Size(min=1, max=1)
    private String gender;

}
