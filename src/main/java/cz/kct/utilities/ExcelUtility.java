package cz.kct.utilities;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@UtilityClass

public class ExcelUtility {
    public Optional<Workbook> getWorkBook(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Optional<Workbook> wb = Optional.of(WorkbookFactory.create(fis));
            return wb;
        } catch(IOException e) {
            log.error("Wrong file name. {}", e.getMessage());
            return Optional.ofNullable(null);
        }
    }
    public void getValue(Workbook wb, String tableName) {
        Sheet sheet = wb.getSheet(tableName);
        for (Row myrow : sheet) {
            System.out.println("New row: ");
            for (Cell mycell : myrow) {
                System.out.println(mycell);
            }
        }
    }
}
