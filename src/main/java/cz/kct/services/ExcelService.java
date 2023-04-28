package cz.kct.services;

import cz.kct.exceptions.ExcelException;
import cz.kct.repository.DzcRepository;
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
    public static final String FILE_PATH = "C:\\Users\\belysheva\\Downloads\\exportdohnalek.xls";
    private final String tableName = "Worklogs";
    private final ExcelRepository excelRepository;
    private final DzcService dzcService;

    /**
     * Method getting objects from xls files
     *
     * @return Success message
     * @throws ExcelException
     */
    public String readFromFile() throws ExcelException {
        Optional<Workbook> vOutput = ExcelUtility.getWorkBook(FILE_PATH);
        if (vOutput.isPresent()) {
            log.info("reading data ... ");
            ExcelUtility.getValue(vOutput.get(), tableName, excelRepository, dzcService);
            return "Data was accepted";
        } else {
            throw new ExcelException("File path is not valid");
        }
    }
}
