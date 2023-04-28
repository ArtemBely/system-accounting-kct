package cz.kct.repository;

import cz.kct.data.dto.DzcDto;
import cz.kct.data.entity.TimeSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExcelRepository extends JpaRepository<TimeSheetEntity, Integer> {
//    List<TimeSheetEntity> findByDzc(DzcDto dzc);
}