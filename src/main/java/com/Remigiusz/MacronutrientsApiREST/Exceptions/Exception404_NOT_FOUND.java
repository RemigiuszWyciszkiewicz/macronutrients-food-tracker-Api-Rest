package com.Remigiusz.MacronutrientsApiREST.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class Exception404_NOT_FOUND extends RuntimeException {
    public Exception404_NOT_FOUND() {
    }

    public Exception404_NOT_FOUND(String message) {
        super(message);
    }

    public Exception404_NOT_FOUND(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception404_NOT_FOUND(Throwable cause) {
        super(cause);
    }
}
