package com.xts.stock.control.entrypoint.interceptor.exceptions;

public class CustomerAlreadyExistException extends RuntimeException {

    public CustomerAlreadyExistException() {
        super("Cliente já cadastrado. Para adicionar mais etiquetas, favor utilizar a página de consulta.");
    }
}
