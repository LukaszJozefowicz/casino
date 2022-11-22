package com.globallogic.casino.service.strategy;

import com.globallogic.casino.model.entity.Customer;
import com.globallogic.casino.model.entity.Game;

import java.util.List;

public interface OperationsStrategy {
    List<Customer> simulateGame();
    boolean checkItems();
}
