package cz.kct.utilities;

import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.data.enums.FixedValuesEnum;
import cz.kct.data.enums.KindEnum;
import cz.kct.utilities.DefinitionUtility;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@UtilityClass

public class ExcelUtility {
    public static final int DECLARATION_FIELD_ROW = 0;
    public static final int ISSUE_KEY_ROW = 0;
    public static final int ISSUE_SUMMARY_ROW = 1;
    public static final int ACCOUNT_NAME_ROW = 8;
    public static final int LOCAL_DATE_ROW = 3;
    public static final int PROJECT_NAME_ROW = 19;
    public static final int HOURS_ROW = 2;
    public static final int DESCRIPTION_ROW = 22;

    private TimeSheetEntity timeSheetEntity;
    private List<TimeSheetEntity> timeSheetEntityArrayList = new ArrayList<>();
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
        timeSheetEntityArrayList.clear();
        for (Row myrow : sheet) {
            System.out.println("New row: " + myrow.getRowNum());
            //log.info("New row: " + myrow.getRowNum()));
            if (myrow.getRowNum() != DECLARATION_FIELD_ROW) addItemToList(myrow);
        }
        for(TimeSheetEntity obj : timeSheetEntityArrayList) {
            System.out.println(obj.toString() + " object");
            //log.info(obj + " object");
        }
    }

    public void addItemToList(Row myrow) {
        try {
            timeSheetEntity = new TimeSheetEntity(
                    UUID.randomUUID(),
                    DateConvertUtility.parseToLocalDate(myrow.getCell(LOCAL_DATE_ROW).toString()),
                    FixedValuesEnum.INVOICED_DAY.getValue(),
                    myrow.getCell(PROJECT_NAME_ROW).toString(),
                    Double.parseDouble(myrow.getCell(HOURS_ROW).toString()),
                    myrow.getCell(ISSUE_KEY_ROW).toString().concat(" " + myrow.getCell(DESCRIPTION_ROW).toString()),
                    DefinitionUtility.defineKind(myrow.getCell(ACCOUNT_NAME_ROW).toString(), myrow.getCell(ISSUE_SUMMARY_ROW).toString())
                );
                timeSheetEntityArrayList.add(timeSheetEntity);
            }
            catch (Exception ex) {
                log.error(ex.getMessage());
            }

    }

}