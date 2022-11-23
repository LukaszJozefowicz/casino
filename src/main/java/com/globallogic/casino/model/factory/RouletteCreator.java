package com.globallogic.casino.model.factory;

import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.enums.GameType;
import com.globallogic.casino.model.enums.ItemType;
import com.globallogic.casino.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class RouletteCreator extends Game {

    private final ItemService itemService;

    @Override
    public Game createGame() {
        return Game.builder()
                .gameType(GameType.ROULETTE)
                .maxPlayers(2)
                .necessaryItem(itemService.getItemToSet(ItemType.ROULETTE_TABLE))
                .necessaryItem(itemService.getItemToSet(ItemType.ROULETTE_BALL))
                .necessaryItem(itemService.getItemToSet(ItemType.SIMPLE_CHAIR))
                .necessaryItem(itemService.getItemToSet(ItemType.SIMPLE_CHAIR))
                .timesPlayed(0)
                .totalIncome(BigDecimal.ZERO)
                .averageIncomePerGame(BigDecimal.ZERO)
                .build();
    }
}
