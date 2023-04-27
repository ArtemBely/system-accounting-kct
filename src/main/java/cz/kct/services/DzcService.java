package cz.kct.services;

import cz.kct.data.dto.TimeSheetDto;
import cz.kct.data.entity.DzcEntity;
import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.data.mapper.ExcelMapper;
import cz.kct.exceptions.ExcelException;
import cz.kct.repository.DzcRepository;
import cz.kct.repository.ExcelRepository;
import cz.kct.utilities.FindAndSaveDzcUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor

public class DzcService {
    private final DzcRepository dzcRepository;
    private final ExcelRepository excelRepository;
    private final ExcelMapper excelMapper;
    public List<TimeSheetEntity> joinTables() {
        log.info("DATA = {}", dzcRepository.getJoinInformation());
        return dzcRepository.getJoinInformation();
    }
    public void insertOne(TimeSheetDto timeSheetDto) throws ExcelException {
        log.info("start process insert entity in services");
        if(timeSheetDto != null) {
            FindAndSaveDzcUtility.findAndSaveDzcIfDoesNotExist(timeSheetDto.getDzc_id().getDzc(), dzcRepository);
            TimeSheetEntity timeSheetEntity = excelMapper.mapToEntity(timeSheetDto);
            excelRepository.save(timeSheetEntity);
        }
        else {
            throw new ExcelException("Item was not presented");
        }
    }

}
