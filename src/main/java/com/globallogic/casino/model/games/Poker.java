package com.globallogic.casino.model.games;

import com.globallogic.casino.model.Game;
import com.globallogic.casino.model.Item;
import com.globallogic.casino.model.enums.GameType;
import com.globallogic.casino.model.enums.ItemCondition;
import com.globallogic.casino.model.enums.ItemType;
import com.globallogic.casino.service.strategy.PokerOperationStrategy;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
public class Poker extends Game {
    @Override
    public Game createGame() {
        return Poker.builder()
                .gameType(GameType.POKER)
                .gameOperationsStrategy(new PokerOperationStrategy())
                .amountOfPlayers(6)
                .necessaryItem(new Item(ItemType.CARD_TABLE, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.POKER_CARDS_SET, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.COMFY_CHAIR, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.COMFY_CHAIR, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.COMFY_CHAIR, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.COMFY_CHAIR, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.COMFY_CHAIR, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.COMFY_CHAIR, ItemCondition.EXCELLENT))
                .timesPlayed(0)
                .totalIncome(BigDecimal.ZERO)
                .averageIncomePerGame(BigDecimal.ZERO)
                .build();
    }
}
