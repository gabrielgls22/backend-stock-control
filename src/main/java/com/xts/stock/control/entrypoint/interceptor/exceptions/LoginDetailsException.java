package com.xts.stock.control.entrypoint.interceptor.exceptions;

public class LoginDetailsException extends RuntimeException {

    public LoginDetailsException() {
        super("Usuário e/ou senha inválidos");
    }
}
