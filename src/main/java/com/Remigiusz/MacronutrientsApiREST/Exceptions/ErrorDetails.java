package com.Remigiusz.MacronutrientsApiREST.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {

    private int status;
    private String msg;
    private String request;

    public ErrorDetails() {
    }
}
