package com.xts.stock.control.entrypoint.interceptor.exceptions;

public class BarcodeDoesNotExistException extends RuntimeException {

    public BarcodeDoesNotExistException(final String message) {
        super(message);
    }
}
