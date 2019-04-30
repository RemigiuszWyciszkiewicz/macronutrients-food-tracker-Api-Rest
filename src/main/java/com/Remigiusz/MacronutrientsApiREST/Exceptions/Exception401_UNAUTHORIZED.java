package com.Remigiusz.MacronutrientsApiREST.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Exception401_UNAUTHORIZED  extends RuntimeException{

    public Exception401_UNAUTHORIZED() {
        super();
    }

    public Exception401_UNAUTHORIZED(String message) {
        super(message);
    }

    public Exception401_UNAUTHORIZED(String message, Throwable cause) {
        super(message, cause);
    }
}
