package baseComponent;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class TestDataFromExcel {

    static Workbook workbook;
    static Sheet sheet;
    public static String Sheet_Path = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.xlsx";

    public Object[][] getExcelSheetData(String sheetName) throws EncryptedDocumentException, IOException {
        FileInputStream file = new FileInputStream(Sheet_Path);
        workbook = WorkbookFactory.create(file);
        sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
            }
        }
        return data;
    }

    @DataProvider
    public Object[][] getData() throws EncryptedDocumentException, IOException {
        return getExcelSheetData("Sheet1");
    }
}
