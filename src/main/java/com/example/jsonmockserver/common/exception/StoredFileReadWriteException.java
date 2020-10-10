package com.example.jsonmockserver.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
public class StoredFileReadWriteException  extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer code;


    public StoredFileReadWriteException() {
    }

    public StoredFileReadWriteException(String message) {
        super(message);
    }

    public StoredFileReadWriteException(String message, int code) {
        super(message);
        this.code = code;
    }
}
