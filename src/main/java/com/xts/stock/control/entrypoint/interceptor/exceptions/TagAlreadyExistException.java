package com.xts.stock.control.entrypoint.interceptor.exceptions;

public class TagAlreadyExistException extends RuntimeException {

    public TagAlreadyExistException(final String tagName, final String tagCode) {
        super(String.format("O código %s já existe no banco de dados para a etiqueta: %s", tagCode, tagName));
    }
}
