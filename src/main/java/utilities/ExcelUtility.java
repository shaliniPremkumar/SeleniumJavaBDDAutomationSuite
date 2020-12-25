package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtility {
    /*
    This is an excel utility class, helps with the excel functions such as reading from excel and storing as a Map
    */

    private String filePath;
    private String sheetName;

    public ExcelUtility(String filePath, String sheetName) {
        this.filePath = filePath;
        this.sheetName = sheetName;
    }
    private XSSFSheet getSheet() throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        return sheet;
    }
    public Map<String, Map<String, String> > getExcelAsMap() throws IOException {
        XSSFSheet sheet = getSheet();
        Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();
        List<String> columnHeader = new ArrayList<String>();
        List<String> primaryKey = new ArrayList<String>();
        Row row = sheet.getRow(0);
        Iterator< Cell > cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            columnHeader.add(cellIterator.next().getStringCellValue());
        }
        int rowCount = sheet.getLastRowNum();
        int columnCount = columnHeader.size();
        for (int i = 1; i <= rowCount; i++) {
            Map<String, String> singleRowData = new HashMap<String, String>();
            Row row1 = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row1.getCell(j);
                singleRowData.put(columnHeader.get(j), getCellValueAsString(cell));
                if(j==0) {
                    primaryKey.add(getCellValueAsString(cell));
                }
            }
            completeSheetData.put(primaryKey.get(i-1), singleRowData);
        }
        return completeSheetData;
    }
    public String getCellValueAsString(Cell cell) {
        String cellValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue= cell.getCellFormula();
            case BLANK:
                cellValue="BLANK";
            default:
                cellValue ="DEFAULT";
        }
        return cellValue;
    }
}