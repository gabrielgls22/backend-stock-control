package com.xts.stock.control.entrypoint.interceptor.exceptions;

public class CryptDecryptException extends RuntimeException {

    public CryptDecryptException(final String message, final Throwable error) {
        super(message, error);
    }
}
