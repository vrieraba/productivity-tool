package com.vriera.productivity.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class ExcelUtils {

    public List<Map<String, String>> readFile(InputStream inputStream) throws IOException {
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        HashMap<Integer, String> headers = null;

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        int rowNum = 0;
        Row row;
        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            if (rowNum == 0) {
                headers = createExcelHeaders(row);
            } else {
                rows.add(createExcelColumns(row, headers));
            }
            rowNum++;
        }
        workbook.close();

        return rows;
    }
    private HashMap<Integer, String> createExcelHeaders(Row row) {
        HashMap<Integer, String> headers = new HashMap<Integer, String>();
        Iterator<Cell> cellIterator = row.cellIterator();
        Cell celda;
        int numCell = 0;
        while (cellIterator.hasNext()){
            celda = cellIterator.next();
            headers.put(numCell, celda.getStringCellValue());
            numCell++;
        }
        return headers;
    }

    private Map<String, String> createExcelColumns(Row row, HashMap<Integer, String> headers) {
        Map<String, String> columns = new HashMap<String, String>();
        Iterator<Cell> cellIterator = row.cellIterator();
        Cell celda;
        int numCell = 0;
        while (cellIterator.hasNext()){
            celda = cellIterator.next();
            String value = null;
            switch(celda.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    if(DateUtil.isCellDateFormatted(celda) ){
                        value = celda.getDateCellValue().toString();
                    }else{
                        value = String.valueOf((int) celda.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = celda.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = String.valueOf(celda.getBooleanCellValue());
                    break;
            }
            columns.put(headers.get(numCell), value);
            numCell++;
        }
        return columns;
    }



}
