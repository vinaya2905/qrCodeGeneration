package com.barcode.springbootqrcodegeneratorapp.exception;

import com.barcode.springbootqrcodegeneratorapp.model.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyCellException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse emptyCellException(EmptyCellException ex) {
        return new ApiErrorResponse(Instant.now(),
                ex.errorCode,
                ex.msg,
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(InvalidColumnException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse invalidColumn(InvalidColumnException ex)
    {
        return new ApiErrorResponse(Instant.now(),
                ex.errorCode,
                ex.msg,
                HttpStatus.BAD_REQUEST.value());
    }
}
