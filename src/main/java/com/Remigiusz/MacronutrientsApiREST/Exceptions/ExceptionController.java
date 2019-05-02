package com.Remigiusz.MacronutrientsApiREST.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionController {


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails> response404(Exception404_NOT_FOUND e, WebRequest webRequest)
    {
        ErrorDetails customerErrorResponse=new ErrorDetails(HttpStatus.NOT_FOUND.value(),e.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customerErrorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDetails> response400(Exception400_BAD_REQUEST e, WebRequest webRequest)
    {
        ErrorDetails customerErrorResponse=new ErrorDetails(HttpStatus.BAD_REQUEST.value(),e.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customerErrorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler()
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDetails> invalid(MethodArgumentNotValidException e, WebRequest webRequest)
    {
        System.out.println(e.getLocalizedMessage());
        System.out.println(e.getBindingResult());
        System.out.println(e.getParameter());
        ErrorDetails customerErrorResponse=new ErrorDetails(HttpStatus.BAD_REQUEST.value(),e.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customerErrorResponse, HttpStatus.BAD_REQUEST);
    }



}
