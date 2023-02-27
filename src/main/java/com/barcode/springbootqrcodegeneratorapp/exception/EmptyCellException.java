package com.barcode.springbootqrcodegeneratorapp.exception;

public class EmptyCellException extends RuntimeException{
    String errorCode;
    String msg;

    public EmptyCellException(String errorCode,String msg)
    {
        this.errorCode=errorCode;
        this.msg=msg;
    }

}
