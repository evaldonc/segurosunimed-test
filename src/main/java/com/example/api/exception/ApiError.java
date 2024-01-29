package com.example.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    private Integer code;

    private String status;

    private List<String> errors;

}
