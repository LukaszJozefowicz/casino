package com.globallogic.casino.model;

import com.globallogic.casino.model.enums.GameType;
import com.globallogic.casino.service.strategy.GameOperationsStrategy;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameId;
    private GameType gameType;
    @OneToOne
    private Employee assignedEmployee;
    @Transient
    private GameOperationsStrategy gameOperationsStrategy;
    private Integer amountOfPlayers;
    @Singular
    @OneToMany
    private List<Item> necessaryItems;
    @OneToMany
    private List<Customer> players;
    private Integer timesPlayed;
    private BigDecimal totalIncome;
    private BigDecimal averageIncomePerGame;

    public abstract Game createGame();
}
