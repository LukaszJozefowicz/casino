package com.globallogic.casino.service.strategy;

import com.globallogic.casino.model.entity.Customer;
import com.globallogic.casino.model.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public abstract class GameOperationsStrategy implements OperationsStrategy {

    private List<Item> necessaryItems;
    private List<Customer> players;

//    abstract void simulateGame();
//    abstract boolean checkItems();
}
