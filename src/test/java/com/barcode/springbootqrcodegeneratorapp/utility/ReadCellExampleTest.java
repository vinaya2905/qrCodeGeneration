package com.barcode.springbootqrcodegeneratorapp.utility;

import com.barcode.springbootqrcodegeneratorapp.config.GlobalMessageSource;
import com.barcode.springbootqrcodegeneratorapp.config.PropertiesConfiguration;
import com.barcode.springbootqrcodegeneratorapp.exception.EmptyCellException;
import com.barcode.springbootqrcodegeneratorapp.exception.InvalidColumnException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReadCellExampleTest {

    @Mock
    GlobalMessageSource globalMessageSource;
    @Mock
    PropertiesConfiguration propertiesConfiguration;
    @InjectMocks
    ReadCellExample readCellExample;

    int val;
    @BeforeEach
    public void setUp()
    {
        val=2;
    }


    @Test
    void readCellData() throws Exception {
        List<String> list=new ArrayList<>();
        list.add("RAZOLE");
        list.add("AMBAJIPETA");
        list.add("ALLAVARAM");
        Mockito.when(propertiesConfiguration.getFileName()).thenReturn("src/test/resources/testdata/Data.xlsx");
        Assertions.assertEquals(list,readCellExample.readCellData(val));
    }

    @Test
    void testReadCellData() {
        List<Integer> li=new ArrayList<>();
        li.add(2);
        li.add(4);
        List<String> list=new ArrayList<>();
        list.add("RAZOLEKAMBALA BABU RAO");
        list.add("AMBAJIPETAMAHAMMAD MEER USMAN ALI");
        list.add("ALLAVARAMDOMMETI  VENKARESWARA RAO");
        Mockito.when(propertiesConfiguration.getFileName()).thenReturn("src/test/resources/testdata/Data.xlsx");
        Assertions.assertEquals(list,readCellExample.readCellData(li));
    }

    @Test
    void readCellDataEmptyCellException()
    {
        Mockito.when(propertiesConfiguration.getFileName()).thenReturn("src/test/resources/testdata/EmptyCellData.xlsx");
        Assertions.assertThrows(EmptyCellException.class,()->readCellExample.readCellData(val));
    }
    @Test
    void readCellDataEmptyCellExceptionMulIds()
    {
        List<Integer> li=new ArrayList<>();
        li.add(2);
        li.add(4);
        Mockito.when(propertiesConfiguration.getFileName()).thenReturn("src/test/resources/testdata/EmptyCellData.xlsx");
        Assertions.assertThrows(EmptyCellException.class,()->readCellExample.readCellData(li));
    }
    @Test
    void readCellDataInvalidColumnException()
    {
        List<Integer> li=new ArrayList<>();
        li.add(2);
        li.add(null);
        Mockito.when(propertiesConfiguration.getFileName()).thenReturn("src/test/resources/testdata/Data.xlsx");
        Assertions.assertThrows(InvalidColumnException.class,()->readCellExample.readCellData(li));
    }
}