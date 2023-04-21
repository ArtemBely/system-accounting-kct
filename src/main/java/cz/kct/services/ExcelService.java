package cz.kct.services;

import cz.kct.data.dto.TimeSheetDto;
import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.data.mapper.ExcelMapper;
import cz.kct.exceptions.ExcelException;
import cz.kct.repository.ExcelRepository;
import cz.kct.utilities.ExcelUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.ExcelStyleDateFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private ExcelMapper excelMapper;

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

    public void insert(List<TimeSheetDto> items) throws ExcelException {
        log.info("start process insert entity in services");
        if(items != null) {
            for(TimeSheetDto item : items) {
                TimeSheetEntity timeSheetEntity = excelMapper.mapToEntity(item);
                log.info("start process into db {}", timeSheetEntity);
                excelRepository.save(timeSheetEntity);
                log.info("end process insert item in services");
            }
        }
        else {
            throw new ExcelException("Items array is not valid");
        }
    }

    public void insertTest(TimeSheetDto dto) {
        log.info("start process insert person in services");
        TimeSheetEntity timeSheetEntity = excelMapper.mapToEntity(dto);
        log.info("start process into db {}", timeSheetEntity);
        excelRepository.save(timeSheetEntity);
        log.info("end process insert person in services");
    }
}
