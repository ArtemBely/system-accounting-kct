package cz.kct.controllers;

import cz.kct.data.dto.TimeSheetDto;
import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.services.DzcService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/manual")
public class InputManualController {
    private final DzcService dzcService;
    @Operation(summary="save")
    @PostMapping("/v1/save")
    public void insertOne(@Valid @RequestBody TimeSheetDto timeSheetDto) {
        try{
            log.info("start process insert item in controller: {}",  timeSheetDto);
            dzcService.insertOne(timeSheetDto);
            log.info("end process insert item in controller: {}",  timeSheetDto);
        }
        catch (Exception ex) {
            log.error("Error message", ex);
        }
    }
    @GetMapping("/v1/join")
    public List<TimeSheetEntity> joinDzcAndTickets() {
        return dzcService.joinTables();
    }
}
