package cz.kct.utilities;

import cz.kct.data.dto.DzcDto;
import cz.kct.data.entity.DzcEntity;
import cz.kct.exceptions.ExcelException;
import cz.kct.repository.DzcRepository;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@UtilityClass
public class FindAndSaveDzcUtility {
    public void findAndSaveDzcIfDoesNotExist(String dzc, DzcRepository dzcRepository) throws ExcelException {
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
