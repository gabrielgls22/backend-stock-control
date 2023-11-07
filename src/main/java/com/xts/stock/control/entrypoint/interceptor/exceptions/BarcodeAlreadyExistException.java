package com.xts.stock.control.entrypoint.interceptor.exceptions;

public class BarcodeAlreadyExistException extends RuntimeException {

    public BarcodeAlreadyExistException(final String message) {
        super(message);
    }
}
