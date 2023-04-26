package cz.kct.services;

import cz.kct.data.entity.DzcEntity;
import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.exceptions.ExcelException;
import cz.kct.repository.DzcRepository;
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
    public List<TimeSheetEntity> joinTables() {
        log.info("DATA IS = {}", dzcRepository.getJoinInformation());
        return dzcRepository.getJoinInformation();
    }
    public void findAndSaveDzcIfDoesNotExist(String dzc) throws ExcelException {
        if(dzc != null) {
            log.info("Start to find dzc into database");
            Optional<DzcEntity> dzcEntityOptional = dzcRepository.findById(dzc);
            if(!dzcEntityOptional.isPresent()) {
                dzcRepository.save(new DzcEntity(dzc));
                log.info("DZC was saved");
            }
        }
        else throw new ExcelException("Dzc doesn't accepted");
    }
}
