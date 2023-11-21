package com.xts.stock.control.entrypoint.interceptor.exceptions;

public class BarcodeConsultException extends RuntimeException {

    public BarcodeConsultException(final String barcode) {
        super(String.format("O material com código de barras %s não existe no estoque.", barcode));
    }
}
