package com.globallogic.casino.model.dto.mongo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameReportDto {
    private Integer timesPlayed;
    private String totalIncome;
    private String averageIncomePerGame;
}
