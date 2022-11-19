package com.globallogic.casino.model.games;

import com.globallogic.casino.model.Game;
import com.globallogic.casino.model.Item;
import com.globallogic.casino.model.enums.GameType;
import com.globallogic.casino.model.enums.ItemCondition;
import com.globallogic.casino.model.enums.ItemType;
import com.globallogic.casino.service.strategy.SlotMachineOperationStrategy;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
public class SlotMachine extends Game {
    @Override
    public Game createGame() {
        return SlotMachine.builder()
                .gameType(GameType.SLOT_MACHINE)
                .gameOperationsStrategy(new SlotMachineOperationStrategy())
                .amountOfPlayers(1)
                .necessaryItem(new Item(ItemType.SLOT_MACHINE_BOX, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.SLOT_MACHINE_BUTTONS, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.SLOT_MACHINE_ELECTRONICS, ItemCondition.EXCELLENT))
                .necessaryItem(new Item(ItemType.HIGH_BAR_CHAIR, ItemCondition.EXCELLENT))
                .timesPlayed(0)
                .totalIncome(BigDecimal.ZERO)
                .averageIncomePerGame(BigDecimal.ZERO)
                .build();
    }
}
