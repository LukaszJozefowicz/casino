package com.globallogic.casino.service.strategy;

import com.globallogic.casino.model.entity.Customer;
import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.model.entity.Item;
import com.globallogic.casino.model.enums.ItemType;
import com.globallogic.casino.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static com.globallogic.casino.model.enums.ItemCondition.POOR;
import static com.globallogic.casino.model.enums.ItemType.*;

public class PokerOperationStrategy extends GameOperationsStrategy implements OperationsStrategy {

    private static final List<ItemType> ITEMS_NEEDED_FOR_POKER = List.of(
            CARD_TABLE, POKER_CARDS_SET, COMFY_CHAIR, COMFY_CHAIR, COMFY_CHAIR);
    private static final BigDecimal POKER_COMMISSION_FEE = BigDecimal.valueOf(0.05);

    public PokerOperationStrategy(List<Item> necessaryItems, List<Customer> players) {
        super(necessaryItems, players);
    }

    @Override
    public List<Customer> simulateGame() {
        List<Customer> players = getPlayers();
        BigDecimal pot = BigDecimal.ZERO;
        for (Customer player : players) {
            pot = pot.add(player.getCurrentBalanceInTokens());
            player.setCurrentBalanceInTokens(BigDecimal.ZERO);
        }
        BigDecimal commissionFee = pot.multiply(POKER_COMMISSION_FEE);
        pot = pot.subtract(commissionFee);

        Random rand = new Random();
        Customer winner = players.get(rand.nextInt(players.size()));
        for (Customer player : players) {
            if (player.equals(winner)) {
                player.setCurrentBalanceInTokens(pot);
            } else {
                player.setCurrentBalanceInTokens(BigDecimal.ZERO);
            }
        }
        return players;
    }

    @Override
    public boolean checkItems() {
        boolean areAllItemsPresent = getNecessaryItems().stream()
                .map(Item::getItemType)
                .allMatch(ITEMS_NEEDED_FOR_POKER::contains);
        boolean areAllItemsInGoodCondition = getNecessaryItems().stream()
                .map(Item::getItemCondition)
                .noneMatch(itemCondition -> itemCondition.equals(POOR));
        return areAllItemsPresent && areAllItemsInGoodCondition;
    }
}
