package com.globallogic.casino.model.games;

import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.model.enums.GameType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Roulette extends Game {
    @Override
    public Game createGame() {
        return Game.builder()
                .gameType(GameType.ROULETTE)
                .maxPlayers(2)
//                .necessaryItem(new Item(ItemType.ROULETTE_TABLE, ItemCondition.EXCELLENT))
//                .necessaryItem(new Item(ItemType.ROULETTE_BALL, ItemCondition.EXCELLENT))
//                .necessaryItem(new Item(ItemType.SIMPLE_CHAIR, ItemCondition.EXCELLENT))
//                .necessaryItem(new Item(ItemType.SIMPLE_CHAIR, ItemCondition.EXCELLENT))
                .timesPlayed(0)
                .totalIncome(BigDecimal.ZERO)
                .averageIncomePerGame(BigDecimal.ZERO)
                .build();
    }
}
