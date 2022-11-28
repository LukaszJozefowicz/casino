package com.globallogic.casino.controller;

import com.globallogic.casino.model.dto.mongo.DailyReportDto;
import com.globallogic.casino.service.DailyReportService;
import com.globallogic.casino.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/management")
public class ManagementController {

    private final PersonService personService;
    private final DailyReportService dailyReportService;

    @GetMapping("/removeOrphanedAddresses")
    public String removeOrphanedAddresses() {
        return personService.removeOrphanedAddresses();
    }

    @GetMapping("/generateReport")
    public DailyReportDto generateReport() {
        return dailyReportService.generateDailyReport();
    }
}
