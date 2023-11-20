package com.xts.stock.control.entrypoint.interceptor;

import com.xts.stock.control.entrypoint.interceptor.dto.ErrorDto;
import com.xts.stock.control.entrypoint.interceptor.exceptions.BarcodeAlreadyExistException;
import com.xts.stock.control.entrypoint.interceptor.exceptions.BarcodeDoesNotExistException;
import com.xts.stock.control.entrypoint.interceptor.exceptions.StandardException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    private static final String ARGUMENT_NOT_VALID_EXCEPTION = "Um ou mais campos não estão sendo enviados corretamente.";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException methodArgumentNotValidException) {

        log.debug(methodArgumentNotValidException.getMessage());

        final ErrorDto errorDto = ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ARGUMENT_NOT_VALID_EXCEPTION)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(StandardException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<ErrorDto> handleStandardException(
            final StandardException standardException) {

        log.debug(standardException.getMessage());

        final ErrorDto errorDto = ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(standardException.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(BarcodeDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<ErrorDto> handleBarcodeDoesNotExistException(
            final BarcodeDoesNotExistException barcodeDoesNotExistException) {

        log.debug(barcodeDoesNotExistException.getMessage());

        final ErrorDto errorDto = ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("O material com código de barras " + barcodeDoesNotExistException.getMessage() +
                        " não existe no estoque. Nenhum material cadastrado." )
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(BarcodeAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<ErrorDto> handleBarcodeAlreadyExistException(
            final BarcodeAlreadyExistException barcodeAlreadyExistException) {

        log.debug(barcodeAlreadyExistException.getMessage());

        final ErrorDto errorDto = ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("O material com código de barras " + barcodeAlreadyExistException.getMessage() +
                        " já existe no estoque. Nenhum material cadastrado." )
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
