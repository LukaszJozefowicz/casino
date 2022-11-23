package com.globallogic.casino.model.strategy;

import com.globallogic.casino.model.entity.h2.Game;

public class RouletteOperationStrategy extends GameOperationsStrategy {
    public RouletteOperationStrategy(Game game) {
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
