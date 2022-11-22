package com.globallogic.casino.model.games;

import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.model.enums.GameType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SlotMachine extends Game {
    @Override
    public Game createGame() {
        return SlotMachine.builder()
                .gameType(GameType.SLOT_MACHINE)
                .maxPlayers(1)
//                .necessaryItem(new Item(ItemType.SLOT_MACHINE_BOX, ItemCondition.EXCELLENT))
//                .necessaryItem(new Item(ItemType.SLOT_MACHINE_BUTTONS, ItemCondition.EXCELLENT))
//                .necessaryItem(new Item(ItemType.SLOT_MACHINE_ELECTRONICS, ItemCondition.EXCELLENT))
//                .necessaryItem(new Item(ItemType.HIGH_BAR_CHAIR, ItemCondition.EXCELLENT))
                .timesPlayed(0)
                .totalIncome(BigDecimal.ZERO)
                .averageIncomePerGame(BigDecimal.ZERO)
                .build();
    }
}
