package com.Remigiusz.MacronutrientsApiREST.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionController {


    @ExceptionHandler
    public ResponseEntity<ErrorDetails> response404(Exception404_NOT_FOUND e, WebRequest webRequest)
    {
        ErrorDetails customerErrorResponse=new ErrorDetails(HttpStatus.NOT_FOUND.value(),e.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customerErrorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> response400(Exception400_BAD_REQUEST e, WebRequest webRequest)
    {
        ErrorDetails customerErrorResponse=new ErrorDetails(HttpStatus.BAD_REQUEST.value(),e.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customerErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> generalresponse(Exception e, WebRequest webRequest)
    {
        ErrorDetails customerErrorResponse=new ErrorDetails(HttpStatus.BAD_REQUEST.value(),e.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customerErrorResponse, HttpStatus.BAD_REQUEST);
    }



}
