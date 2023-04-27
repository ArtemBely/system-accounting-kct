package cz.kct.controllers;

import cz.kct.data.entity.TimeSheetEntity;
import cz.kct.services.DzcService;
import cz.kct.services.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")

public class Test {
    private final ExcelService excelService;
    @GetMapping("/v1/get")
    public void showData() {
        try {
            log.info("start process of getting data from excel");
            excelService.readFromFile();
            log.info("data from excel was received");
        }
        catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
