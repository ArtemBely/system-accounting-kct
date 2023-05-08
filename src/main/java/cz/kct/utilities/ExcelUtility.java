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

    /**
     * Method for receiving workbook with actual data from file
     *
     * @param filePath define location of file in local computer
     * @return Wrapper of workbook
     */
    public Optional<Workbook> getWorkBook(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Optional<Workbook> wb = Optional.of(WorkbookFactory.create(fis));
            return wb;
        } catch (IOException ex) {
            log.error("Error message: ", ex);
            return Optional.ofNullable(null);
        }
    }
}