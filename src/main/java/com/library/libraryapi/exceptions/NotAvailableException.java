package com.library.libraryapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class NotAvailableException extends RuntimeException{
    public NotAvailableException(String message) {super(message);}
}
