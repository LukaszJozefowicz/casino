package com.globallogic.casino.model.strategy;

import com.globallogic.casino.model.entity.h2.Customer;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.enums.ItemType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.globallogic.casino.model.enums.ItemType.*;

public class BlackJackOperationStrategy extends GameOperationsStrategy {

    private static final List<ItemType> ITEMS_NEEDED_FOR_BLACKJACK = List.of(
            CARD_TABLE, BLACKJACK_CARDS_SET, COMFY_CHAIR);
    private static final int BLACKJACK_WIN_x2_PERCENTAGE = 44;
    private static final String GAME_RESULT_MSG = """
            BLACK JACK (1 player)
            Player: %s made a bet of %s
            If player wins, he gets twice the amount
            Player %s
            Balance change for casino: %s""";

    public BlackJackOperationStrategy(Game game) {
        super(game);
    }

    @Override
    public Game simulateGame(BigDecimal playerBet) {
        playerBet = playerBet.setScale(2, RoundingMode.CEILING);
        Game gamePlayed = getGame();
        Customer player = getGame().getPlayers().get(0);

        placeBetFromPlayer(player, playerBet);
        boolean isWin = determineGameWin(player, playerBet);
        BigDecimal totalAmountForCasino = isWin ? playerBet.negate() : playerBet;
        updateAfterGameParams(gamePlayed, playerBet, isWin, totalAmountForCasino);
        return gamePlayed;
    }

    @Override
    public boolean checkItems() {
        return super.checkItems(ITEMS_NEEDED_FOR_BLACKJACK);
    }

    private void placeBetFromPlayer(Customer player, BigDecimal playerBet) {
        BigDecimal playerCurrentBalance = player.getCurrentBalance();
        player.setCurrentBalance(playerCurrentBalance.subtract(playerBet));
    }

    private boolean determineGameWin(Customer player, BigDecimal playerBet) {
        if (isWinByPercentage(BLACKJACK_WIN_x2_PERCENTAGE)) {
            BigDecimal amountWon = playerBet.multiply(BigDecimal.valueOf(2L));
            player.setCurrentBalance(player.getCurrentBalance().add(amountWon));
            return true;
        }
        return false;
    }

    private void updateAfterGameParams(Game game, BigDecimal playerBet, boolean isWin, BigDecimal totalAmountForCasino) {
        updateAfterGameCommonParams(game, totalAmountForCasino);
        game.setGameResultMessage(String.format(GAME_RESULT_MSG,
                game.getPlayers().get(0).getFirstName() + " " + game.getPlayers().get(0).getLastName(),
                playerBet.toPlainString(),
                isWin ? "won" : "lost",
                totalAmountForCasino.toPlainString()));
    }
}
