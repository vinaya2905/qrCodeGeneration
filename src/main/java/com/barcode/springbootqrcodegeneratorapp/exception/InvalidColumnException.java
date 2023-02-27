package com.barcode.springbootqrcodegeneratorapp.exception;

public class InvalidColumnException extends RuntimeException{
    String errorCode;
    String msg;

    public InvalidColumnException(String errorCode,String msg)
    {
        this.errorCode=errorCode;
        this.msg=msg;
    }
}
