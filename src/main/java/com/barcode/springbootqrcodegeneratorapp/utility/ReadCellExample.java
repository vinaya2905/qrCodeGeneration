package com.barcode.springbootqrcodegeneratorapp.utility;

import com.barcode.springbootqrcodegeneratorapp.config.GlobalMessageSource;
import com.barcode.springbootqrcodegeneratorapp.config.PropertiesConfiguration;
import com.barcode.springbootqrcodegeneratorapp.exception.EmptyCellException;
import com.barcode.springbootqrcodegeneratorapp.exception.InvalidColumnException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.barcode.springbootqrcodegeneratorapp.exception.ErrorConstants.CELL_IS_EMPTY;
import static com.barcode.springbootqrcodegeneratorapp.exception.ErrorConstants.COLUMN_IS_EMPTY;

@Component
@AllArgsConstructor
public class ReadCellExample
{

    private PropertiesConfiguration propertiesConfiguration;

//    @Value("${file.name}")
//    String location;
    private GlobalMessageSource globalMessageSource;

    //Reads data for mentioned cell from excel for every row
    public  List<String> readCellData(int val) {
        List<String> value = new ArrayList<>();
        Workbook wb = null;
        try (FileInputStream fis = new FileInputStream(propertiesConfiguration.getFileName())){
            wb = new XSSFWorkbook(fis);
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        for (int i = 1; i < sheet.getPhysicalNumberOfRows();i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(val);
            //checking if the cell has data
            if(cell==null || cell.getStringCellValue().isBlank())
                throw new EmptyCellException(CELL_IS_EMPTY,globalMessageSource.get(CELL_IS_EMPTY));
            //getting cell value
            value.add(cell.getStringCellValue());
        }
        return value;
    }

    //Reads data for mentioned cells from excel for every row (more than two cells given)
    public  List<String> readCellData(List<Integer> ids)
    {
        List<String> value = new ArrayList<>();
        Workbook wb = null;
        try (FileInputStream fis = new FileInputStream(propertiesConfiguration.getFileName())){
            wb = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        if(CollectionUtils.containsInstance(ids,null) || CollectionUtils.containsInstance(ids," "))
            throw new InvalidColumnException(COLUMN_IS_EMPTY, globalMessageSource.get(COLUMN_IS_EMPTY));
        for (int i = 1; i <sheet.getPhysicalNumberOfRows();i++) {
            StringBuilder c=new StringBuilder();
            for(int j=0;j<ids.size();j++)
            {
                Cell cell = sheet.getRow(i).getCell(ids.get(j));
                //checking if the cell has data
                if(cell==null || cell.getStringCellValue().isBlank())
                    throw new EmptyCellException(CELL_IS_EMPTY,globalMessageSource.get(CELL_IS_EMPTY));
                c.append(cell.getStringCellValue());
            }
            value.add(String.valueOf(c));
        }
        return value;
    }
}

