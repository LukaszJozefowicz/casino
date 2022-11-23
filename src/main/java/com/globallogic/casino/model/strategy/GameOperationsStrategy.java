package com.globallogic.casino.model.strategy;

import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.entity.h2.Item;
import com.globallogic.casino.model.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.globallogic.casino.model.enums.ItemCondition.POOR;

@AllArgsConstructor
@Getter
@Setter
public abstract class GameOperationsStrategy {

    private Game game;

    public abstract Game simulateGame(long playerBet);
    public abstract boolean checkItems();

    public boolean checkItems(List<ItemType> itemsNeeded) {
        boolean areAllItemsPresent = getGame().getNecessaryItems().stream()
                .map(Item::getItemType)
                .allMatch(itemsNeeded::contains);
        boolean areAllItemsInGoodCondition = getGame().getNecessaryItems().stream()
                .map(Item::getItemCondition)
                .noneMatch(itemCondition -> itemCondition.equals(POOR));
        return areAllItemsPresent && areAllItemsInGoodCondition;
    }
}
