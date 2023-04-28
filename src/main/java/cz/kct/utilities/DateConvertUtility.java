package cz.kct.utilities;

import cz.kct.exceptions.ExcelException;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass

public class DateConvertUtility {
    /**
     * Method for converting string into LocalDate object
     *
     * @param dateFromSheet incoming string, candidate to date-format string
     * @return string parsed into LocalDate object
     * @throws ExcelException
     */
    public LocalDate parseToLocalDate(String dateFromSheet) throws ExcelException {
        if (dateFromSheet != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            return LocalDate.parse(dateFromSheet, formatter);
        } else throw new ExcelException("Date doesn't exists");
    }
}
