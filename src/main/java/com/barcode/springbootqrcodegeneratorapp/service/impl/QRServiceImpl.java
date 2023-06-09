package com.barcode.springbootqrcodegeneratorapp.service.impl;

import com.barcode.springbootqrcodegeneratorapp.service.IQRService;
import com.barcode.springbootqrcodegeneratorapp.utility.ReadCellExample;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import java.awt.geom.RoundRectangle2D;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.barcode.springbootqrcodegeneratorapp.constants.QRCodeConstants.BASE_URL;


@Service
public class QRServiceImpl implements IQRService {

    @Autowired
    private  ReadCellExample readCellExample;


@Override
public void generate(int id, int width, int height, String filepath, int col) {
    HashMap<String,String> txt= readCellExample.readCellData(id,col);
    QRCodeWriter qrCodeWriter = new QRCodeWriter();

    for (Map.Entry<String, String> entry : txt.entrySet()) {
        BitMatrix bitMatrix;
        try {
            PdfReader reader = new PdfReader("/home/vinayasree/Downloads/Front-final (1).pdf");
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(filepath+entry.getValue()+".pdf"));

            // Get the first (and only) page of the PDF document
            PdfContentByte contentByte = stamper.getOverContent(1);

//            Get the dimensions of the page
            Rectangle pageSize = reader.getPageSize(1);
            bitMatrix = qrCodeWriter.encode(BASE_URL + entry.getKey(), BarcodeFormat.QR_CODE, width, height);
//            Path path = FileSystems.getDefault().getPath(filepath + entry.getValue() + ".png");
//            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            // Create an iText image from the QR code image
            Image pdfImage = Image.getInstance(qrImage, null, false);
            // Set the position and size of the image on the page
            float w = (pageSize.getWidth() - pdfImage.getScaledWidth()) / 2;
            float h = (pageSize.getHeight() - pdfImage.getScaledHeight()) / 1.91f;
            pdfImage.setAbsolutePosition(w,h);

//            pdfImage.setAbsolutePosition((pageSize.getLeft() + pageSize.getRight()) / 3.05f, (pageSize.getBottom() + pageSize.getTop()) / 2.82f);
            pdfImage.scaleToFit(qrImage.getWidth(), qrImage.getHeight());
            // Add the image to the page of the new PDF document
            contentByte.addImage(pdfImage);
            // Add the text below the QR code
            String text = entry.getValue();
            float x = (pageSize.getLeft() + pageSize.getRight()) / 2;
            float y = (pageSize.getBottom() + pageSize.getTop()) / 1.94f - qrImage.getHeight()/2 -1; // adjust the vertical position as needed
            contentByte.beginText();
            // set the font and font size
            contentByte.setFontAndSize(BaseFont.createFont(), 15);
            // align the text to the center of the page
            contentByte.showTextAligned(Element.ALIGN_CENTER, text, x, y, 0);
            contentByte.endText();
            // Close the stamper and the reader
            stamper.close();
            reader.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}

    @Override
    public void generate(List<Integer> ids, int width, int height, String filepath,int col) {
        HashMap<String,String > txt= readCellExample.readCellData(ids,col);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        for (Map.Entry<String, String> entry : txt.entrySet()) {
            BitMatrix bitMatrix;
            try {
                bitMatrix = qrCodeWriter.encode(entry.getKey(), BarcodeFormat.QR_CODE, width, height);
                Path path = FileSystems.getDefault().getPath(filepath + entry.getValue() + ".png");
                MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}

