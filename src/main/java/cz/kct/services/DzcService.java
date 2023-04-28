package cz.kct.services;

import cz.kct.data.dto.DzcDto;
import cz.kct.data.dto.TimeSheetDto;
import cz.kct.data.entity.DzcEntity;
import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.data.mapper.DzcMapper;
import cz.kct.data.mapper.ExcelMapper;
import cz.kct.exceptions.ExcelException;
import cz.kct.repository.DzcRepository;
import cz.kct.repository.ExcelRepository;
import cz.kct.utilities.FindDzcUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public void insertOne(TimeSheetDto timeSheetDto) throws ExcelException {
        log.info("start process insert entity in services");
        if (timeSheetDto != null) {
            DzcDto dzcDto = dzcMapper.mapToDto(timeSheetDto.getDzc_id());
            insertDzcIfDoesNotExist(dzcDto);
            TimeSheetEntity timeSheetEntity = excelMapper.mapToEntity(timeSheetDto);
            excelRepository.save(timeSheetEntity);
            //checkIfDatesAreIncompatible(timeSheetDto.getDzc_id());
        } else {
            throw new ExcelException("Item was not presented");
        }
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

//    public List<TimeSheetEntity> checkIfDatesAreIncompatible(DzcDto dzc) {
//        return excelRepository.findByDzc(dzc);
//    }
}
