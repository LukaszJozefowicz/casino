package com.globallogic.casino.model.entity.mongo;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Document
@NoArgsConstructor
@Getter
@Setter
public class DailyReport {
    @Id
    private Long id;
    @CreatedDate
    private LocalDate reportDate;
    private BigDecimal totalIncome;
    private Integer totalGamesPlayed;
    private GameReport pokerReport;
    private GameReport blackJackReport;
    private GameReport rouletteReport;
    private GameReport slotMachinesReport;
}
