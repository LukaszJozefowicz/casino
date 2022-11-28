package com.globallogic.casino.model.strategy;

import com.globallogic.casino.model.entity.h2.Customer;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.enums.ItemType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.globallogic.casino.model.enums.ItemType.*;

public class SlotMachineOperationStrategy extends GameOperationsStrategy {
    private static final List<ItemType> ITEMS_NEEDED_FOR_SLOT_MACHINE = List.of(
            SLOT_MACHINE_BOX, SLOT_MACHINE_BUTTONS, SLOT_MACHINE_ELECTRONICS, HIGH_BAR_CHAIR);
    private static final int SLOT_MACHINE_WIN_x5_PERCENTAGE = 16;
    private static final String GAME_RESULT_MSG = """
            SLOT MACHINE (1 player)
            Player: %s made a bet of %s
            If player wins, he gets five times the amount
            Player %s
            Balance change for casino: %s""";

    public SlotMachineOperationStrategy(Game game) {
        super(game);
    }

    @Override
    public Game simulateGame(BigDecimal playerBet) {
        playerBet = playerBet.setScale(2, RoundingMode.CEILING);
        Game gamePlayed = getGame();
        Customer player = getGame().getPlayers().get(0);

        placeBetFromPlayer(player, playerBet);
        boolean isWin = determineGameWin(player, playerBet);
        BigDecimal totalAmountForCasino = isWin ? playerBet.multiply(BigDecimal.valueOf(4L)).negate() : playerBet;
        updateAfterGameParams(gamePlayed, playerBet, isWin, totalAmountForCasino);
        return gamePlayed;
    }

    @Override
    public boolean checkItems() {
        return super.checkItems(ITEMS_NEEDED_FOR_SLOT_MACHINE);
    }

    private void placeBetFromPlayer(Customer player, BigDecimal playerBet) {
        BigDecimal playerCurrentBalance = player.getCurrentBalance();
        player.setCurrentBalance(playerCurrentBalance.subtract(playerBet));
    }

    private boolean determineGameWin(Customer player, BigDecimal playerBet) {
        if (isWinByPercentage(SLOT_MACHINE_WIN_x5_PERCENTAGE)) {
            BigDecimal amountWon = playerBet.multiply(BigDecimal.valueOf(5L));
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
                isWin
                        ? "won " + playerBet.multiply(BigDecimal.valueOf(5L))
                        : "lost " + playerBet,
                totalAmountForCasino.toPlainString()));
    }
}
