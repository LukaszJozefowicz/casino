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
public class SlotMachineCreator extends Game {

    private final ItemService itemService;

    @Override
    public Game createGame() {
        return SlotMachineCreator.builder()
                .gameType(GameType.SLOT_MACHINE)
                .maxPlayers(1)
                .necessaryItem(itemService.getItemToSet(ItemType.SLOT_MACHINE_BOX))
                .necessaryItem(itemService.getItemToSet(ItemType.SLOT_MACHINE_BUTTONS))
                .necessaryItem(itemService.getItemToSet(ItemType.SLOT_MACHINE_ELECTRONICS))
                .necessaryItem(itemService.getItemToSet(ItemType.HIGH_BAR_CHAIR))
                .timesPlayed(0)
                .totalIncome(BigDecimal.ZERO)
                .averageIncomePerGame(BigDecimal.ZERO)
                .build();
    }
}
