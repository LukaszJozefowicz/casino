package com.globallogic.casino.model.dto;

import com.globallogic.casino.model.entity.h2.Customer;
import com.globallogic.casino.model.entity.h2.Item;
import com.globallogic.casino.model.enums.GameType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class GameDto {
    private Long gameId;
    private GameType gameType;
    private Integer maxPlayers;
    private List<ItemDto> necessaryItems;
    private List<CustomerDto> players;
    private Integer timesPlayed;
    private BigDecimal totalIncome;
    private BigDecimal averageIncomePerGame;
}
