package com.barcode.springbootqrcodegeneratorapp.service;

import com.barcode.springbootqrcodegeneratorapp.config.GlobalMessageSource;
import com.barcode.springbootqrcodegeneratorapp.service.impl.QRServiceImpl;
import com.barcode.springbootqrcodegeneratorapp.utility.ReadCellExample;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    int col;
    @BeforeEach
    public void init()
    {
        id=2;
        list.add("Ramya");
        list.add("Sudha");
        height = 100;
        width = 100;
        col=2;

    }
    @Test
    void genrateAndDownloadQRCodeTest() throws IOException {
        String filepath="src/test/resources/testdata/";
        when(readCellExample.readCellData(id,col)).thenReturn(new HashMap<>());
        assertDoesNotThrow(()->qrService.generate(id,width,height,filepath, col));
    }
    @Test
    void generateQRcodewithMultipleIdTest()
    {
        List<Integer> li= new ArrayList<>();
        li.add(1);
        li.add(2);
        String filepath="src/test/resources/testdata/";
        when(readCellExample.readCellData(li,col)).thenReturn(new HashMap<>());
        assertDoesNotThrow(()->qrService.generate(li,width,height,filepath,col));
    }

}
