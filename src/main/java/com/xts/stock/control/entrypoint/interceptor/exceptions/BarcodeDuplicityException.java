package com.xts.stock.control.entrypoint.interceptor.exceptions;

public class BarcodeDuplicityException extends RuntimeException {

    public BarcodeDuplicityException(final String message) {
        super(message);
    }
}
