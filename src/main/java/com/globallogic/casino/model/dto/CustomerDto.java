package com.globallogic.casino.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.globallogic.casino.model.entity.Game;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDto {
    private Long customerId;
    private BigDecimal currentBalanceInTokens;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTimePresent;
    private GameDto currentlyPlayedGame;
}
