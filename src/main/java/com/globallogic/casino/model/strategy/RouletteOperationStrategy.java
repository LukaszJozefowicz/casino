package com.globallogic.casino.model.strategy;

import com.globallogic.casino.model.entity.h2.Customer;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.enums.ItemType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import static com.globallogic.casino.model.enums.ItemType.*;

public class RouletteOperationStrategy extends GameOperationsStrategy {

    private static final List<ItemType> ITEMS_NEEDED_FOR_ROULETTE = List.of(
            ROULETTE_TABLE, ROULETTE_BALL, SIMPLE_CHAIR, SIMPLE_CHAIR);
    private static final int ROULETTE_WIN_x2_PERCENTAGE = 45;
    private static final String GAME_RESULT_MSG = """
            ROULETTE (2 players)
            Player 1: %s and Player 2: %s made bets of %s each
            Winner gets twice the amount of bet
            Players won: %s amount: %s
            Balance change for casino: %s""";

    public RouletteOperationStrategy(Game game) {
        super(game);
    }

    @Override
    public Game simulateGame(BigDecimal playerBet) {
        playerBet = playerBet.setScale(2, RoundingMode.CEILING);
        Game gamePlayed = getGame();
        List<Customer> players = gamePlayed.getPlayers();

        BigDecimal totalAmountForCasino = placeBetsFromPlayers(players, playerBet);
        List<String> winners = determineGameWinners(players, playerBet);
        totalAmountForCasino = updateTotalAmountForCasino(Integer.toUnsignedLong(winners.size()), playerBet, totalAmountForCasino);
        updateAfterGameParams(gamePlayed, playerBet, winners, totalAmountForCasino);
        return gamePlayed;
    }

    @Override
    public boolean checkItems() {
        return super.checkItems(ITEMS_NEEDED_FOR_ROULETTE);
    }

    private BigDecimal placeBetsFromPlayers(List<Customer> players, BigDecimal playerBet) {
        for (Customer player : players) {
            BigDecimal playerCurrentBalance = player.getCurrentBalance();
            player.setCurrentBalance(playerCurrentBalance.subtract(playerBet));
        }
        return playerBet.multiply(BigDecimal.valueOf(2L));
    }

    private List<String> determineGameWinners(List<Customer> players, BigDecimal playerBet) {
        List<String> winners = new LinkedList<>();
        for (Customer player : players) {
            if (isWinByPercentage(ROULETTE_WIN_x2_PERCENTAGE)) {
                BigDecimal amountWon = playerBet.multiply(BigDecimal.valueOf(2L));
                player.setCurrentBalance(player.getCurrentBalance().add(amountWon));
                winners.add(player.getFirstName() + " " + player.getLastName());
            }
        }
        return winners;
    }

    private BigDecimal updateTotalAmountForCasino(Long howManyWinners, BigDecimal playerBet, BigDecimal totalAmountForCasino) {
        BigDecimal amountToSubtract = playerBet.multiply(BigDecimal.valueOf(2L * howManyWinners));
        totalAmountForCasino = totalAmountForCasino.subtract(amountToSubtract);
        return totalAmountForCasino;
    }

    private void updateAfterGameParams(Game game, BigDecimal playerBet, List<String> winners, BigDecimal totalAmountForCasino) {
        updateAfterGameCommonParams(game, totalAmountForCasino);
        game.setGameResultMessage(String.format(GAME_RESULT_MSG,
                game.getPlayers().get(0).getFirstName() + " " + game.getPlayers().get(0).getLastName(),
                game.getPlayers().get(1).getFirstName() + " " + game.getPlayers().get(1).getLastName(),
                playerBet.toPlainString(),
                winners.toString(),
                playerBet.multiply(BigDecimal.valueOf(2L)).toPlainString(),
                totalAmountForCasino.toPlainString()));
    }
}
