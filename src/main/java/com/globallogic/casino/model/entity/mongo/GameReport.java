package com.globallogic.casino.model.entity.mongo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GameReport {
    private Integer timesPlayed;
    private String totalIncome;
    private String averageIncomePerGame;
}
