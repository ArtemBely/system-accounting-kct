package cz.kct.services;

import cz.kct.data.dto.DzcDto;
import cz.kct.data.dto.TimeSheetDto;
import cz.kct.data.entity.DzcEntity;
import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.data.enums.FixedValuesEnum;
import cz.kct.data.mapper.DzcMapper;
import cz.kct.data.mapper.ExcelMapper;
import cz.kct.exceptions.ExcelException;
import cz.kct.repository.DzcRepository;
import cz.kct.repository.ExcelRepository;
import cz.kct.utilities.FindDzcUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor

public class DzcService {
    private final DzcRepository dzcRepository;
    private final ExcelRepository excelRepository;
    private final ExcelMapper excelMapper;
    private final DzcMapper dzcMapper;

    /**
     * Method for joining Excel object with DZC records
     *
     * @return joined table (DZC & Excel object)
     */
    public List<TimeSheetEntity> joinTables() {
        log.info("DATA = {}", dzcRepository.getJoinInformation());
        return dzcRepository.getJoinInformation();
    }

    /**
     * Method for saving Excel object + control if compatible DZC record exist.
     *
     * @param timeSheetDto incoming Excel object
     * @throws ExcelException
     */
    public void insertOne(TimeSheetDto timeSheetDto) {
        log.info("start process insert entity in services");
        try {
            DzcDto dzcDto = dzcMapper.mapToDto(timeSheetDto.getDzc_id());
            insertDzcIfDoesNotExist(dzcDto);
            TimeSheetEntity timeSheetEntity = excelMapper.mapToEntity(timeSheetDto);
            checkIfDatesAreIncompatible(dzcDto, timeSheetDto);
            excelRepository.save(timeSheetEntity);
        } catch (ResponseStatusException ex) {
            log.error("Error message: ", ex);
        }
    }

    /**
     * Method find list of dzc records for same date when customer insert One record and compare
     * if Invoice day (800) in insertable date is not match to Sick day or Holiday in already existing records.
     * If incorrect codes match method go throw ResponseStatusException.
     *
     * @param dzc          search the list of records with same dzc value
     * @param timeSheetDto incoming timesheet record
     * @throws ResponseStatusException
     * @see cz.kct.data.enums.FixedValuesEnum
     */
    public void checkIfDatesAreIncompatible(DzcDto dzc, TimeSheetDto timeSheetDto) throws ResponseStatusException {
        getAllRecordsByDzc(dzc)
                .stream()
                .filter(item -> item.getDate().equals(timeSheetDto.getDate()))
                .forEach(item -> {
                    if (item.getTypeOfItem() == FixedValuesEnum.INVOICED_DAY.getValue() &&
                            (timeSheetDto.getTypeOfItem() == FixedValuesEnum.SICK_DAY.getValue() ||
                                    timeSheetDto.getTypeOfItem() == FixedValuesEnum.HOLIDAY.getValue())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not compatible type of code. Please enter correct code for this date.");
                    }
                });
    }

    /**
     * Method for inserting DZC object in database if it doesn't exist
     *
     * @param dzcDto incoming Excel object
     * @see FindDzcUtility#findDzc(String, DzcRepository)
     */
    public void insertDzcIfDoesNotExist(DzcDto dzcDto) {
        try {
            if (!FindDzcUtility.findDzc(dzcDto.getDzc(), dzcRepository)) {
                dzcRepository.save(new DzcEntity(dzcDto.getDzc()));
            }
        } catch (Exception ex) {
            log.error("Error message: ", ex);
        }
    }

    /**
     * Method find all items by incoming Dzc from POST request
     *
     * @param dzc incoming dzc value
     * @return List of suitable records from database
     */
    public List<TimeSheetEntity> getAllRecordsByDzc(DzcDto dzc) {
        return dzcRepository.findByDzc(dzcMapper.mapToEntity(dzc));
    }
}
