package com.globallogic.casino.model.strategy;

import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.entity.h2.Item;
import com.globallogic.casino.model.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Random;

import static com.globallogic.casino.model.enums.ItemCondition.POOR;

@AllArgsConstructor
@Getter
@Setter
public abstract class GameOperationsStrategy {

    private Game game;

    public abstract Game simulateGame(BigDecimal playerBet);
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

    public boolean isWinByPercentage(int winPercentage) {
        int randomPercentage = new Random().nextInt(100) + 1;
        return randomPercentage <= winPercentage;
    }

    public void updateItemsCondition(Game game) {
        if (game.getTimesPlayed() % 3 == 0) {
            for (Item item : game.getNecessaryItems()) {
                item.setItemCondition(item.getItemCondition().getOneLowerCondition());
            }
        }
    }

    public void updateAfterGameCommonParams(Game game, BigDecimal totalAmountForCasino) {
        game.setTimesPlayed(game.getTimesPlayed() + 1);
        game.setTotalIncome(game.getTotalIncome().add(totalAmountForCasino));
        game.setAverageIncomePerGame(game.getTotalIncome()
                .divide(BigDecimal.valueOf(getGame().getTimesPlayed()), new MathContext(15)));
        updateItemsCondition(game);
    }
}
