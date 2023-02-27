package com.barcode.springbootqrcodegeneratorapp.service.impl;

import com.barcode.springbootqrcodegeneratorapp.service.IQRService;
import com.barcode.springbootqrcodegeneratorapp.utility.ReadCellExample;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import static com.barcode.springbootqrcodegeneratorapp.constants.QRCodeConstants.BASE_URL;

@Service
public class QRServiceImpl implements IQRService {

    @Autowired
    private  ReadCellExample readCellExample;
    @Override
    public void generate(int id, int width, int height, String filepath)  {
        List<String> txt= readCellExample.readCellData(id);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        for(int i=0;i< txt.size();i++) {
            BitMatrix bitMatrix;
            try {
                bitMatrix = qrCodeWriter.encode(BASE_URL + txt.get(i), BarcodeFormat.QR_CODE, width, height);
                Path path = FileSystems.getDefault().getPath(filepath + txt.get(i) + ".png");
                MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void generate(List<Integer> ids, int width, int height, String filepath) {
        List<String> txt= readCellExample.readCellData(ids);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        for(int i=0;i< txt.size();i++) {
            BitMatrix bitMatrix;
            try {
                bitMatrix = qrCodeWriter.encode(txt.get(i), BarcodeFormat.QR_CODE, width, height);
                Path path = FileSystems.getDefault().getPath(filepath + txt.get(i) + ".png");
                MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            }
             catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
