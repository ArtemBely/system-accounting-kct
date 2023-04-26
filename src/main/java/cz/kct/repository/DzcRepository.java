package cz.kct.repository;

import cz.kct.data.entity.DzcEntity;
import cz.kct.data.entity.TimeSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DzcRepository extends JpaRepository<DzcEntity, String> {
    @Query("SELECT p FROM TimeSheetEntity p JOIN p.dzc_id s")
    List<TimeSheetEntity> getJoinInformation();
}
