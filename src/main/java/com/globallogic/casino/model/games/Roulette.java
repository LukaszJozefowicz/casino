package com.globallogic.casino.model.games;

import com.globallogic.casino.model.Game;
import com.globallogic.casino.model.Item;
import com.globallogic.casino.model.enums.GameType;
import com.globallogic.casino.model.enums.ItemCondition;
import com.globallogic.casino.model.enums.ItemType;
import com.globallogic.casino.service.strategy.RouletteOperationStrategy;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
public class Roulette extends Game {
    @Override
    public Game createGame() {
        return Roulette.builder()
                .gameType(GameType.ROULETTE)
                .gameOperationsStrategy(new RouletteOperationStrategy())
                .amountOfPlayers(2)
                .necessaryItem(new Item(ItemType.ROULETTE_TABLE, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.ROULETTE_BALL, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.SIMPLE_CHAIR, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.SIMPLE_CHAIR, ItemCondition.EXCELLENT))
                .timesPlayed(0)
                .totalIncome(BigDecimal.ZERO)
                .averageIncomePerGame(BigDecimal.ZERO)
                .build();
    }
}
