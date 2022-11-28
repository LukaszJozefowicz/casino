package com.globallogic.casino.model.entity.h2;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"players", "necessaryItems", "assignedEmployee"})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameType gameType;
    @OneToOne(mappedBy = "assignedGame")
    @JsonBackReference(value = "emp-game-ref")
    private Employee assignedEmployee;
    @Column(nullable = false)
    private Integer maxPlayers;
    @Singular
    @OneToMany(mappedBy = "assignedToGame")
    @JsonBackReference
    private List<Item> necessaryItems;
    @OneToMany(mappedBy = "currentlyPlayedGame")
    @JsonBackReference
    @Builder.Default
    private List<Customer> players = new LinkedList<>();
    @Column(nullable = false)
    private Integer timesPlayed;
    @Column(nullable = false)
    private BigDecimal totalIncome;
    @Column(nullable = false)
    private BigDecimal averageIncomePerGame;
    @Transient
    private String gameResultMessage;

    public Game createGame() {
        return this;
    }
}
