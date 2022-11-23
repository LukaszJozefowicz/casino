package com.globallogic.casino.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globallogic.casino.model.enums.GameType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDto {
    private Long customerId;
    private BigDecimal currentBalance;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTimePresent;
    private Long currentlyPlayedGameId;
    private GameType currentlyPlayedGameType;
}
