package com.Remigiusz.MacronutrientsApiREST.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Exception400_BAD_REQUEST extends RuntimeException {
    public Exception400_BAD_REQUEST() {
        super();
    }

    public Exception400_BAD_REQUEST(String message) {
        super(message);
    }

    public Exception400_BAD_REQUEST(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception400_BAD_REQUEST(Throwable cause) {
        super(cause);
    }
}
