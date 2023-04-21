package cz.kct.controllers;

import cz.kct.data.dto.TimeSheetDto;
import cz.kct.services.ExcelService;
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
    private final ExcelService excelService;
    @Operation(summary="creation")
    @PostMapping("/v1/save")
    public void insert(@Valid @RequestBody List<TimeSheetDto> timeSheetDto) {
        try{
            log.info("start process insert list of items in controller: {}",  timeSheetDto);
            excelService.insert(timeSheetDto);
            log.info("end process insert list of items in controller: {}",  timeSheetDto);
        }
        catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    @PostMapping("/v1/test")
    public void insertTest(@Valid @RequestBody TimeSheetDto timeSheetDto) {
        log.info("start process insert list of items in controller: {}",  timeSheetDto);
        excelService.insertTest(timeSheetDto);
        log.info("end process insert list of items in controller: {}",  timeSheetDto);
    }
}
