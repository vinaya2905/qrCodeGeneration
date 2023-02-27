package com.barcode.springbootqrcodegeneratorapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.time.Instant;

import static com.barcode.springbootqrcodegeneratorapp.constants.QRCodeConstants.DATE_PATTERN;
import static com.barcode.springbootqrcodegeneratorapp.constants.QRCodeConstants.TIME_ZONE;

@Value
public class ApiErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = TIME_ZONE)
    Instant timestamp;
    String error;
    String message;
    int status;
}
