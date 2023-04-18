package cz.kct.services;

import cz.kct.exceptions.ExcelException;
import cz.kct.repository.ExcelRepository;
import cz.kct.utilities.ExcelUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor

public class ExcelService {
    public static final int ROW = 0;
    public static final int COLUMN = 0;
    //public static final String FILE_PATH = "C:\\Users\\belysheva\\Downloads\\tool_v6 (1).xlsm";
    public static final String FILE_PATH = "C:\\Users\\belysheva\\Downloads\\exportdohnalek.xls";
    private final String tableName = "Worklogs";
    private final ExcelRepository excelRepository;

    public String readFromFile() throws ExcelException {
        Optional<Workbook> vOutput = ExcelUtility.getWorkBook(FILE_PATH);
        if(vOutput.isPresent()) {
            log.info("reading data ... ");
            ExcelUtility.getValue(vOutput.get(), tableName);
            return "Data was accepted";
        }
        else {
            throw new ExcelException("File path is not valid");
        }
    }
}
