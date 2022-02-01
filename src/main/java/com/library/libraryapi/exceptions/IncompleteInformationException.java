package com.library.libraryapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncompleteInformationException extends RuntimeException{
    public IncompleteInformationException(String message){super(message);}
}
