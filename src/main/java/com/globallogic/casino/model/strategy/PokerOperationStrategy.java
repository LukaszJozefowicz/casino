package com.globallogic.casino.model.strategy;

import com.globallogic.casino.model.entity.h2.Customer;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.enums.ItemType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

import static com.globallogic.casino.model.enums.ItemType.*;

public class PokerOperationStrategy extends GameOperationsStrategy {

    private static final List<ItemType> ITEMS_NEEDED_FOR_POKER = List.of(
            CARD_TABLE, POKER_CARDS_SET, COMFY_CHAIR, COMFY_CHAIR, COMFY_CHAIR);
    private static final BigDecimal POKER_COMMISSION_FEE = BigDecimal.valueOf(0.05);
    private static final String GAME_RESULT_MSG = """
            POKER (3 players)
            Player %s %s won %s
            Other players lost %s each
            Commission fee taken by casino: %s""";

    public PokerOperationStrategy(Game game) {
        super(game);
    }

    @Override
    public Game simulateGame(BigDecimal playerBet) {
        playerBet = playerBet.setScale(2, RoundingMode.CEILING);
        Game gamePlayed = getGame();
        List<Customer> players = gamePlayed.getPlayers();

        BigDecimal pot = placeBetsFromPlayers(players, playerBet);
        BigDecimal commissionFee = calculateCommissionFee(pot);
        pot = pot.subtract(commissionFee);

        Customer winner = determineGameWinner(players, pot);
        updateAfterGameParams(gamePlayed, commissionFee, playerBet, pot, winner);
        return gamePlayed;
    }

    @Override
    public boolean checkItems() {
        return super.checkItems(ITEMS_NEEDED_FOR_POKER);
    }

    private BigDecimal placeBetsFromPlayers(List<Customer> players, BigDecimal playerBet) {
        BigDecimal pot = BigDecimal.ZERO;
        for (Customer player : players) {
            pot = pot.add(playerBet);
            BigDecimal playerCurrentBalance = player.getCurrentBalance();
            player.setCurrentBalance(playerCurrentBalance.subtract(playerBet));
        }
        return pot;
    }

    private BigDecimal calculateCommissionFee(BigDecimal pot) {
        return pot.multiply(POKER_COMMISSION_FEE).setScale(2, RoundingMode.CEILING);
    }

    private Customer determineGameWinner(List<Customer> players, BigDecimal pot) {
        Random rand = new Random();
        Customer winner = players.get(rand.nextInt(players.size()));
        winner.setCurrentBalance(winner.getCurrentBalance().add(pot));
        return winner;
    }

    private void updateAfterGameParams(Game game, BigDecimal commissionFee, BigDecimal playerBet, BigDecimal pot, Customer winner) {
        updateAfterGameCommonParams(game, commissionFee);
        game.setGameResultMessage(String.format(GAME_RESULT_MSG,
                winner.getFirstName(),
                winner.getLastName(),
                pot.toPlainString(),
                playerBet.toPlainString(),
                commissionFee.toPlainString()));
    }
}
