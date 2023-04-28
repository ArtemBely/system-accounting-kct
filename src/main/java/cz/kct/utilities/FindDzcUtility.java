package cz.kct.utilities;

import cz.kct.data.entity.DzcEntity;
import cz.kct.exceptions.ExcelException;
import cz.kct.repository.DzcRepository;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@UtilityClass
public class FindDzcUtility {
    /**
     * Method for finding incoming DZC in database
     *
     * @param dzc           unique dzc number
     * @param dzcRepository repository for serving of DZC object
     * @throws ExcelException
     */
    public boolean findDzc(String dzc, DzcRepository dzcRepository) throws ExcelException {
        if (dzc != null) {
            log.info("Start to find dzc into database: {}", dzc);
            Optional<DzcEntity> dzcEntityOptional = dzcRepository.findById(dzc);
            return dzcEntityOptional.isPresent();
        } else throw new ExcelException("Dzc doesn't accepted");
    }
}
