package com.example.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnimedRestException extends Exception{

    private Integer code;

    private String message;

}
