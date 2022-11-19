package com.globallogic.casino.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailyReport {
    private LocalDate reportDate;
    private BigDecimal totalIncome;
    private Integer totalGamesPlayed;
    private Integer rouletteTimesPlayed;
    private Integer slotMachineTimesPlayed;
    private Integer pokerTimesPlayed;
    private Integer blackjackTimesPlayed;
    private Integer totalCustomersServed;
    private Integer totalNewCustomersRegistered;
    private Integer totalCustomersRemoved;
}
