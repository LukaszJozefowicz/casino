package com.globallogic.casino.model.strategy;

import com.globallogic.casino.model.entity.h2.Game;

public class BlackJackOperationStrategy extends GameOperationsStrategy {
    public BlackJackOperationStrategy(Game game) {
        super(game);
    }

    @Override
    public Game simulateGame(long playerBet) {
        return null;
    }

    @Override
    public boolean checkItems() {
        return true;
    }

}
