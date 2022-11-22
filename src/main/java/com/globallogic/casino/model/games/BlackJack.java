package com.globallogic.casino.model.games;

import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.model.enums.GameType;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BlackJack extends Game {
    @Override
    public Game createGame() {
        return Game.builder()
                .gameType(GameType.BLACKJACK)
                .maxPlayers(1)
//                .necessaryItem(new Item(ItemType.CARD_TABLE, ItemCondition.EXCELLENT))
//                .necessaryItem(new Item(ItemType.BLACKJACK_CARDS_SET, ItemCondition.EXCELLENT))
//                .necessaryItem(new Item(ItemType.COMFY_CHAIR, ItemCondition.EXCELLENT))
                .timesPlayed(0)
                .totalIncome(BigDecimal.ZERO)
                .averageIncomePerGame(BigDecimal.ZERO)
                .build();
    }
}
