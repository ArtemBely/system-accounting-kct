package cz.kct.utilities;

import cz.kct.data.entity.DzcEntity;
import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.data.enums.FixedValuesEnum;
import cz.kct.repository.DzcRepository;
import cz.kct.repository.ExcelRepository;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@UtilityClass

public class ExcelUtility {
    public static final int DECLARATION_FIELD_ROW = 0;
    public static final int DEFINITION_DZC_ROW = 1;
    public static final int ISSUE_KEY_ROW = 0;
    public static final int ISSUE_SUMMARY_ROW = 1;
    public static final int ACCOUNT_NAME_ROW = 8;
    public static final int LOCAL_DATE_ROW = 3;
    public static final int DZC_ROW = 4;
    public static final int HOURS_ROW = 2;
    public static final int DESCRIPTION_ROW = 22;
    private TimeSheetEntity timeSheetEntity;
    public Optional<Workbook> getWorkBook(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Optional<Workbook> wb = Optional.of(WorkbookFactory.create(fis));
            return wb;
        } catch(IOException ex) {
            log.error("Error message: ", ex);
            return Optional.ofNullable(null);
        }
    }

    public void getValue(Workbook wb, String tableName, ExcelRepository excelRepository, DzcRepository dzcRepository) {
        Sheet sheet = wb.getSheet(tableName);
        for (Row myrow : sheet) {
            if (myrow.getRowNum() != DECLARATION_FIELD_ROW)
                    addItemToList(myrow, excelRepository, dzcRepository);
        }
    }

    public void addItemToList(Row myrow, ExcelRepository excelRepository, DzcRepository dzcRepository) {
        try {
            if (myrow.getRowNum() == DEFINITION_DZC_ROW) FindAndSaveDzcUtility.findAndSaveDzcIfDoesNotExist(myrow.getCell(DZC_ROW).toString(), dzcRepository);
            timeSheetEntity = new TimeSheetEntity(
                    DateConvertUtility.parseToLocalDate(myrow.getCell(LOCAL_DATE_ROW).toString()),
                    FixedValuesEnum.INVOICED_DAY.getValue(),
                    Double.parseDouble(myrow.getCell(HOURS_ROW).toString()),
                    ProjectNumberUtility.defineProjectNumber(myrow.getCell(ACCOUNT_NAME_ROW).toString(), myrow.getCell(ISSUE_SUMMARY_ROW).toString()),
                    myrow.getCell(ISSUE_KEY_ROW).toString().concat(" " + myrow.getCell(DESCRIPTION_ROW).toString()),
                    DefinitionUtility.defineKind(myrow.getCell(ACCOUNT_NAME_ROW).toString(), myrow.getCell(ISSUE_SUMMARY_ROW).toString()),
                    new DzcEntity(myrow.getCell(DZC_ROW).toString())
                );
                excelRepository.save(timeSheetEntity);
            }
            catch (Exception ex) {
                log.error("Error message: ", ex);
            }

    }
}