package com.globallogic.casino.model.strategy;

import com.globallogic.casino.model.entity.h2.Customer;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.enums.ItemType;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

import static com.globallogic.casino.model.enums.ItemType.*;

public class PokerOperationStrategy extends GameOperationsStrategy {

    private static final List<ItemType> ITEMS_NEEDED_FOR_POKER = List.of(
            CARD_TABLE, POKER_CARDS_SET, COMFY_CHAIR, COMFY_CHAIR, COMFY_CHAIR);
    private static final BigDecimal POKER_COMMISSION_FEE = BigDecimal.valueOf(0.05);

    public PokerOperationStrategy(Game game) {
        super(game);
    }

    @Override
    public Game simulateGame(long playerBet) {
        Game gamePlayed = getGame();
        List<Customer> players = gamePlayed.getPlayers();
        BigDecimal pot = BigDecimal.ZERO;
        for (Customer player : players) {
            pot = pot.add(BigDecimal.valueOf(playerBet));
            BigDecimal playerCurrentBalance = player.getCurrentBalance();
            player.setCurrentBalance(playerCurrentBalance.subtract(BigDecimal.valueOf(playerBet)));
        }
        BigDecimal commissionFee = pot.multiply(POKER_COMMISSION_FEE).setScale(2, RoundingMode.CEILING);
        pot = pot.subtract(commissionFee);

        Random rand = new Random();
        Customer winner = players.get(rand.nextInt(players.size()));
        winner.setCurrentBalance(winner.getCurrentBalance().add(pot));

        gamePlayed.setTimesPlayed(getGame().getTimesPlayed() + 1);
        gamePlayed.setTotalIncome(getGame().getTotalIncome().add(commissionFee));
        gamePlayed.setAverageIncomePerGame(getGame().getTotalIncome()
                .divide(BigDecimal.valueOf(getGame().getTimesPlayed()), new MathContext(2)));
        gamePlayed.setGameResultMessage("Player " + winner.getFirstName() + " " + winner.getLastName()
                + " won " + pot + ".\nOther players lost " + playerBet + " each. " +
                "\nCommission fee taken by casino: " + commissionFee);
        return gamePlayed;
    }

    @Override
    public boolean checkItems() {
        return super.checkItems(ITEMS_NEEDED_FOR_POKER);
    }
}
