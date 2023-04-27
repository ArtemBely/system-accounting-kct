package cz.kct.services;

import cz.kct.data.dto.TimeSheetDto;
import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.data.mapper.ExcelMapper;
import cz.kct.exceptions.ExcelException;
import cz.kct.repository.DzcRepository;
import cz.kct.repository.ExcelRepository;
import cz.kct.utilities.ExcelUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor

public class ExcelService {
    public static final String FILE_PATH = "C:\\Users\\belysheva\\Downloads\\exportdohnalek.xls";
    private final String tableName = "Worklogs";
    private final ExcelRepository excelRepository;
    private final DzcRepository dzcRepository;
    public String readFromFile() throws ExcelException {
        Optional<Workbook> vOutput = ExcelUtility.getWorkBook(FILE_PATH);
        if(vOutput.isPresent()) {
            log.info("reading data ... ");
            ExcelUtility.getValue(vOutput.get(), tableName, excelRepository, dzcRepository);
            return "Data was accepted";
        }
        else {
            throw new ExcelException("File path is not valid");
        }
    }
}
