package com.example.demo.application.exceptions;

public class CepNotFoundException extends RuntimeException {
    public CepNotFoundException(String message) {
        super(message);
    }
}
