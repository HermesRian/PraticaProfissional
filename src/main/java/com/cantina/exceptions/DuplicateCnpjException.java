package com.cantina.exceptions;

public class DuplicateCnpjException extends RuntimeException {
    public DuplicateCnpjException(String message) {
        super(message);
    }
}