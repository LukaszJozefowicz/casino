package com.globallogic.casino.model.dto.mongo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DailyReportDto {
    private LocalDateTime reportDate;
    private String totalIncome;
    private Integer totalGamesPlayed;
    private GameReportDto pokerReport;
    private GameReportDto blackJackReport;
    private GameReportDto rouletteReport;
    private GameReportDto slotMachinesReport;
}
