package com.example.api.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

	private Long id;

	private String name;

	private String email;

	private String gender;

}
