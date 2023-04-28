package cz.kct.utilities;

import cz.kct.constants.ExcelUtilityConstants;
import cz.kct.data.dto.DzcDto;
import cz.kct.data.entity.DzcEntity;
import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.data.enums.FixedValuesEnum;
import cz.kct.repository.ExcelRepository;
import cz.kct.services.DzcService;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@UtilityClass

public class ExcelUtility {
    private TimeSheetEntity timeSheetEntity;

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
            timeSheetEntity = new TimeSheetEntity(
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