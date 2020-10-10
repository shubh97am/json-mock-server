package com.example.jsonmockserver.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class InvalidDataException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer code;


    public InvalidDataException() {
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, int code) {
        super(message);
        this.code = code;
    }
}