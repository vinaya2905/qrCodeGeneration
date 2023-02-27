package com.barcode.springbootqrcodegeneratorapp.service;

import com.barcode.springbootqrcodegeneratorapp.config.GlobalMessageSource;
import com.barcode.springbootqrcodegeneratorapp.constants.QRCodeConstants;
import com.barcode.springbootqrcodegeneratorapp.service.impl.QRServiceImpl;
import com.barcode.springbootqrcodegeneratorapp.utility.ReadCellExample;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.barcode.springbootqrcodegeneratorapp.constants.QRCodeConstantsTest.BASE_URL;
import static com.google.zxing.client.j2se.MatrixToImageWriter.writeToPath;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@EnableWebMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QRServiceTest {

    @Mock
    GlobalMessageSource globalMessageSource;
    @Mock
    ReadCellExample readCellExample;
    @InjectMocks
    QRServiceImpl qrService;


    List<String> list =new ArrayList<>();
    int height;
    int width;
    int id;
    @BeforeEach
    public void init()
    {
        id=2;
        list.add("Ramya");
        list.add("Sudha");
        height = 100;
        width = 100;

    }
    @Test
    void genrateAndDownloadQRCodeTest() throws IOException {
        String filepath="src/test/resources/testdata/";
        when(readCellExample.readCellData(id)).thenReturn(new ArrayList<>(List.of("RAZOLE")));
        assertDoesNotThrow(()->qrService.generate(id,width,height,filepath));
    }
    @Test
    void generateQRcodewithMultipleIdTest()
    {
        List<Integer> li= new ArrayList<>();
        li.add(1);
        li.add(2);
        String filepath="src/test/resources/testdata/";
        when(readCellExample.readCellData(li)).thenReturn(new ArrayList<>(List.of("RAZOLE")));
        assertDoesNotThrow(()->qrService.generate(li,width,height,filepath));
    }

}
