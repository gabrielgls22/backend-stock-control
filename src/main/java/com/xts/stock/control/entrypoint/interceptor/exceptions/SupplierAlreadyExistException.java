package com.xts.stock.control.entrypoint.interceptor.exceptions;

public class SupplierAlreadyExistException extends RuntimeException {

    public SupplierAlreadyExistException() {
        super("Fornecedor já cadastrado. Para adicionar mais materiais, favor utilizar a página de consulta.");
    }
}
