package com.xts.stock.control.entrypoint.interceptor.exceptions;

public class UsernameAlreadyExistException extends RuntimeException {

    public UsernameAlreadyExistException() {
        super("Usuário já cadastrado.");
    }
}
