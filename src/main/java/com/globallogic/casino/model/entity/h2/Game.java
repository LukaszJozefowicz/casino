package com.globallogic.casino.model.entity.h2;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globallogic.casino.model.enums.GameType;
import com.globallogic.casino.model.strategy.GameOperationsStrategy;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "GAMES")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;
    @Enumerated(EnumType.STRING)
    @Column(name = "game_type")
    private GameType gameType;
//    @JoinColumn(name = "assigned_employee_id", referencedColumnName = "employee_id")
    @OneToOne(mappedBy = "assignedGame")
    @JsonBackReference
    private Employee assignedEmployee;
    @Column(name = "max_players")
    private Integer maxPlayers;
    @Singular
    @OneToMany(mappedBy = "assignedToGame")
    @JsonBackReference
    private List<Item> necessaryItems;
    @OneToMany(mappedBy = "currentlyPlayedGame")
    @JsonBackReference
    @Builder.Default
    private List<Customer> players = new LinkedList<>();
    @Column(name = "times_played")
    private Integer timesPlayed;
    @Column(name = "total_income")
    private BigDecimal totalIncome;
    @Column(name = "average_income_per_game")
    private BigDecimal averageIncomePerGame;
    @Transient
    private GameOperationsStrategy gameOperationsStrategy;
    @Transient
    private String gameResultMessage;

    public Game createGame() {
        return this;
    }
}
