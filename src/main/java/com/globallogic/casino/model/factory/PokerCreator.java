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
public class PokerCreator extends Game {

    private final ItemService itemService;

    @Override
    public Game createGame() {
        return Game.builder()
                .gameType(GameType.POKER)
                .maxPlayers(3)
                .necessaryItem(itemService.getItemToSet(ItemType.CARD_TABLE))
                .necessaryItem(itemService.getItemToSet(ItemType.POKER_CARDS_SET))
                .necessaryItem(itemService.getItemToSet(ItemType.COMFY_CHAIR))
                .necessaryItem(itemService.getItemToSet(ItemType.COMFY_CHAIR))
                .necessaryItem(itemService.getItemToSet(ItemType.COMFY_CHAIR))
                .timesPlayed(0)
                .totalIncome(BigDecimal.ZERO)
                .averageIncomePerGame(BigDecimal.ZERO)
                .build();
    }

//    @Override
//    public GameOperationsStrategy getStrategy() {
//        return new PokerOperationStrategy(getNecessaryItems(), getPlayers());
//    }
}
