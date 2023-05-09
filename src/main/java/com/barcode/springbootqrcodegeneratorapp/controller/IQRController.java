package com.barcode.springbootqrcodegeneratorapp.controller;

import com.barcode.springbootqrcodegeneratorapp.model.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IQRController {
    @PostMapping("/genrateAndDownloadQRCode}")
    public ApiResponse<Void> genrateAndDownloadQRCode(
            @RequestParam(required = true,name="id") int id,
            @RequestParam(required=true,name="width") Integer width,
            @RequestParam(required = true,name="height") Integer height,
            @RequestParam(required = true,name = "col")int col
            )
            throws Exception;


    @PostMapping("/generateQRCodeWithIds")
    public ApiResponse<Void> generateQRCodeWithMulIds(
            @RequestParam(name="ids") List<Integer> ids,
            @RequestParam(name="width") Integer width,
            @RequestParam(name="height") Integer height,
            @RequestParam(required = true,name = "col")int col);


}
