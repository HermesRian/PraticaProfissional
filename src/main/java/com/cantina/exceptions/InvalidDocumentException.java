package com.cantina.exceptions;

/**
 * Exceção lançada quando um documento (CPF/CNPJ, etc.) é inválido
 */
public class InvalidDocumentException extends RuntimeException {

    public InvalidDocumentException(String message) {
        super(message);
    }

    public InvalidDocumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
