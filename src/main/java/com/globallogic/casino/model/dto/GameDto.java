package com.globallogic.casino.model.dto;

import com.globallogic.casino.model.entity.Customer;
import com.globallogic.casino.model.entity.Item;
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
    private Integer amountOfPlayers;
    private List<Item> necessaryItems;
    private List<Customer> players;
    private Integer timesPlayed;
    private BigDecimal totalIncome;
    private BigDecimal averageIncomePerGame;
}
