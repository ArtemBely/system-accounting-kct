package cz.kct.repository;

import cz.kct.data.entity.DzcEntity;
import cz.kct.data.entity.TimeSheetEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DzcRepository extends JpaRepository<DzcEntity, String> {
    @Query("SELECT t FROM TimeSheetEntity t JOIN t.dzc_id s")
    List<TimeSheetEntity> getJoinInformation();

    /**
     * Select all suitable records by DZC
     *
     * @param dzc_id incoming dzc value
     * @return List of records
     */
    @Modifying
    @Query("SELECT t FROM TimeSheetEntity t WHERE t.dzc_id = :dzc_id")
    List<TimeSheetEntity> findByDzc(@Param("dzc_id") DzcEntity dzc_id);
}
