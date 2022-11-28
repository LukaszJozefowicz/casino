package com.globallogic.casino.model.entity.mongo;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("REPORTS")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class DailyReport {
    @Id
    private String id;
    @CreatedDate
    private LocalDateTime reportDate;
    private String totalIncome;
    private Integer totalGamesPlayed;
    private GameReport pokerReport;
    private GameReport blackJackReport;
    private GameReport rouletteReport;
    private GameReport slotMachinesReport;
}
