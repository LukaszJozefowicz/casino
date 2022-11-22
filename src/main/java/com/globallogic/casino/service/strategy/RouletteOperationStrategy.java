package com.globallogic.casino.service.strategy;

import com.globallogic.casino.model.entity.Customer;
import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.model.entity.Item;

import java.util.List;

public class RouletteOperationStrategy extends GameOperationsStrategy {
    public RouletteOperationStrategy(List<Item> necessaryItems, List<Customer> players) {
        super(necessaryItems, players);
    }

    @Override
    public List<Customer> simulateGame() {
        return null;
    }

    @Override
    public boolean checkItems() {
        return true;
    }
}
