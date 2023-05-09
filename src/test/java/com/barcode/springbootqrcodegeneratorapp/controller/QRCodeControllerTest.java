package com.barcode.springbootqrcodegeneratorapp.controller;

import com.barcode.springbootqrcodegeneratorapp.config.GlobalMessageSource;
import com.barcode.springbootqrcodegeneratorapp.config.PropertiesConfiguration;
import com.barcode.springbootqrcodegeneratorapp.controller.impl.QRCodeController;
import com.barcode.springbootqrcodegeneratorapp.model.ApiResponse;
import com.barcode.springbootqrcodegeneratorapp.service.IQRService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static com.barcode.springbootqrcodegeneratorapp.constants.QRCodeConstantsTest.TEST_ACTIVE_PROFILE;



@ActiveProfiles(TEST_ACTIVE_PROFILE)
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QRCodeControllerTest {
    @Mock
    GlobalMessageSource globalMessageSource;
    @Mock
    IQRService iqrService;

    @Mock
    PropertiesConfiguration propertiesConfiguration;
    @InjectMocks
    QRCodeController qrCodeController;

    String filepath="src/test/resources/testdata/";

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void genrateAndDownloadQRCode() throws Exception
    {
        Mockito.when(propertiesConfiguration.getQrcodeImagepath()).thenReturn("src/test/resources/testdata");
        ApiResponse<Void> qrResponse = qrCodeController.genrateAndDownloadQRCode(1,100,100,1 );
        Assertions.assertNotNull(qrResponse);
    }

    @Test
    void  generateQRCodeWithMulIds()
    {
        List<Integer> li= new ArrayList<>();
        li.add(1);
        li.add(2);

        Mockito.when(propertiesConfiguration.getQrcodeMulti()).thenReturn("src/test/resources/testdata/");
        ApiResponse<Void> qrResponse = qrCodeController.generateQRCodeWithMulIds(li,100,100,1);
        Assertions.assertNotNull(qrResponse);
    }



}
