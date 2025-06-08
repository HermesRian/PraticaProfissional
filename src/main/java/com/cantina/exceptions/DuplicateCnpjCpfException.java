package com.cantina.exceptions;

public class DuplicateCnpjCpfException extends RuntimeException {
    public DuplicateCnpjCpfException(String message) {
        super(message);
    }
}