package cz.kct.repository;

import cz.kct.data.entity.TimeSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelRepository extends JpaRepository<TimeSheetEntity, Integer> {
}