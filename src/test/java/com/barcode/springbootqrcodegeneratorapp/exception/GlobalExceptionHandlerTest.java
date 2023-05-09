package com.barcode.springbootqrcodegeneratorapp.exception;

import com.barcode.springbootqrcodegeneratorapp.config.GlobalMessageSource;
import com.barcode.springbootqrcodegeneratorapp.model.ApiErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static com.barcode.springbootqrcodegeneratorapp.constants.QRCodeConstantsTest.TEST_ACTIVE_PROFILE;

@ActiveProfiles(TEST_ACTIVE_PROFILE)
@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @Mock
    GlobalMessageSource globalMessageSource;

    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @Test
    void emptyCellExceptionTest()
    {
        EmptyCellException emptyCellException=new EmptyCellException("ERROR_1400","Cell value cannot be null or empty");
        ApiErrorResponse response =globalExceptionHandler.emptyCellException(emptyCellException);
        Assertions.assertNotNull(response);
    }

    @Test
    void invalidColumnTest()
    {
        InvalidColumnException invalidColumnException=new InvalidColumnException("ERROR_1401","Column number cannot be null or empty");
        ApiErrorResponse response = globalExceptionHandler.invalidColumn(invalidColumnException);
        Assertions.assertNotNull(response);
    }
}
