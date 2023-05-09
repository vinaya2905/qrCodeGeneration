package com.barcode.springbootqrcodegeneratorapp.controller.impl;

import com.barcode.springbootqrcodegeneratorapp.config.GlobalMessageSource;
import com.barcode.springbootqrcodegeneratorapp.config.PropertiesConfiguration;
import com.barcode.springbootqrcodegeneratorapp.controller.IQRController;
import com.barcode.springbootqrcodegeneratorapp.model.ApiResponse;
import com.barcode.springbootqrcodegeneratorapp.service.IQRService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.barcode.springbootqrcodegeneratorapp.constants.QRCodeConstants.*;

@RestController
@AllArgsConstructor
public class QRCodeController implements IQRController {
    @Autowired
    private IQRService qrCodeGenerator;

    private PropertiesConfiguration propertiesConfiguration;

    @Autowired
    GlobalMessageSource globalMessageSource;
    @PostMapping("/genrateAndDownloadQRCode")
    public ApiResponse<Void> genrateAndDownloadQRCode(
            @RequestParam(required = true,name="id") int id,
            @RequestParam(required=true,name="width") Integer width,
            @RequestParam(required = true,name="height") Integer height,
            @RequestParam(required = true,name = "col")int col){
        qrCodeGenerator.generate(id,width,height,propertiesConfiguration.getQrcodeImagepath(),col);
        return new ApiResponse<>(null,true,globalMessageSource.get(MESSAGE));
    }

    @PostMapping("/generateQRCodeWithIds")
    public ApiResponse<Void> generateQRCodeWithMulIds(
            @RequestParam(name="ids") List<Integer> ids,
            @RequestParam(name="width") Integer width,
            @RequestParam(name="height") Integer height,
            @RequestParam(required = true,name = "col")int col)

             {
                 qrCodeGenerator.generate(ids,width,height,propertiesConfiguration.getQrcodeMulti(),col);
                 return new ApiResponse<>(null,true,globalMessageSource.get(MESSAGE));

    }


}