package cz.kct.services;

import cz.kct.constants.ExcelUtilityConstants;
import cz.kct.data.dto.DzcDto;
import cz.kct.data.entity.DzcEntity;
import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.data.enums.FixedValuesEnum;
import cz.kct.exceptions.ExcelException;
import cz.kct.repository.ExcelRepository;
import cz.kct.utilities.DateConvertUtility;
import cz.kct.utilities.DefinitionUtility;
import cz.kct.utilities.ExcelUtility;
import cz.kct.utilities.ProjectNumberUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
            getValue(vOutput.get(), tableName, excelRepository, dzcService);
            return "Data was accepted";
        } else {
            throw new ExcelException("File path is not valid");
        }
    }

    /**
     * Method for getting data from sheet
     *
     * @param wb              workbook
     * @param tableName       title of actual table in Excel sheet
     * @param excelRepository repository for serving of Excel object
     * @param dzcService      service for implementation DZC methods
     */
    public void getValue(Workbook wb, String tableName, ExcelRepository excelRepository, DzcService dzcService) {
        Sheet sheet = wb.getSheet(tableName);
        for (Row myrow : sheet) {
            if (myrow.getRowNum() != ExcelUtilityConstants.DECLARATION_FIELD_ROW)
                addItemToDatabase(myrow, excelRepository, dzcService);
        }
    }

    /**
     * Method for saving unique record into database
     *
     * @param myrow           actual row in sheet
     * @param excelRepository repository for serving of Excel object
     * @param dzcService      service for implementation DZC methods
     */
    public void addItemToDatabase(Row myrow, ExcelRepository excelRepository, DzcService dzcService) {
        try {
            log.info("add to db: ");
            if (myrow.getRowNum() == ExcelUtilityConstants.DEFINITION_DZC_ROW) {
                dzcService.insertDzcIfDoesNotExist(new DzcDto(myrow.getCell(ExcelUtilityConstants.DZC_ROW).toString()));
            }
            TimeSheetEntity timeSheetEntity = new TimeSheetEntity(
                    DateConvertUtility.parseToLocalDate(myrow.getCell(ExcelUtilityConstants.LOCAL_DATE_ROW).toString()),
                    FixedValuesEnum.INVOICED_DAY.getValue(),
                    Double.parseDouble(myrow.getCell(ExcelUtilityConstants.HOURS_ROW).toString()),
                    ProjectNumberUtility.defineProjectNumber(myrow.getCell(ExcelUtilityConstants.ACCOUNT_NAME_ROW).toString(), myrow.getCell(ExcelUtilityConstants.ISSUE_SUMMARY_ROW).toString()),
                    myrow.getCell(ExcelUtilityConstants.ISSUE_KEY_ROW).toString().concat(" " + myrow.getCell(ExcelUtilityConstants.DESCRIPTION_ROW).toString()),
                    DefinitionUtility.defineKind(myrow.getCell(ExcelUtilityConstants.ACCOUNT_NAME_ROW).toString(), myrow.getCell(ExcelUtilityConstants.ISSUE_SUMMARY_ROW).toString()),
                    new DzcEntity(myrow.getCell(ExcelUtilityConstants.DZC_ROW).toString())
            );
            excelRepository.save(timeSheetEntity);
        } catch (Exception ex) {
            log.error("Error message: ", ex);
        }

    }
}
