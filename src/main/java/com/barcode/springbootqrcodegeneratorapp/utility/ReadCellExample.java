package com.barcode.springbootqrcodegeneratorapp.utility;

import com.barcode.springbootqrcodegeneratorapp.config.GlobalMessageSource;
import com.barcode.springbootqrcodegeneratorapp.config.PropertiesConfiguration;
import com.barcode.springbootqrcodegeneratorapp.exception.EmptyCellException;
import com.barcode.springbootqrcodegeneratorapp.exception.InvalidColumnException;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.barcode.springbootqrcodegeneratorapp.exception.ErrorConstants.CELL_IS_EMPTY;
import static com.barcode.springbootqrcodegeneratorapp.exception.ErrorConstants.COLUMN_IS_EMPTY;

@Component
@AllArgsConstructor
public class ReadCellExample
{

    private PropertiesConfiguration propertiesConfiguration;
    private GlobalMessageSource globalMessageSource;

    //Reads data for mentioned cell from excel for every row
    public HashMap<String,String> readCellData(int val,int col) {
        HashMap<String ,String> value = new HashMap<>();
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
            Cell cell1 =row.getCell(col);
            //checking if the cell has data
            if(cell==null || cell.getStringCellValue().isBlank())
                throw new EmptyCellException(CELL_IS_EMPTY,globalMessageSource.get(CELL_IS_EMPTY));
            //getting cell value
//            value.add(cell.getStringCellValue());
            value.put(cell.getStringCellValue(),cell1.getStringCellValue());
        }
        return value;
    }

    //Reads data for mentioned cells from excel for every row (more than two cells given)
    public  HashMap<String,String> readCellData(List<Integer> ids,int col)
    {
        HashMap<String,String> value = new HashMap<>();

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
                c.append(cell.getStringCellValue()+" ");
            }
            Cell cell1 =sheet.getRow(i).getCell(col);
            value.put(String.valueOf(c),cell1.getStringCellValue());
        }
        return value;
    }
}

