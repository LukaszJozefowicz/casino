package com.globallogic.casino.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Document
public class DailyReport {
    @Id
    private Long id;
    @CreatedDate
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
