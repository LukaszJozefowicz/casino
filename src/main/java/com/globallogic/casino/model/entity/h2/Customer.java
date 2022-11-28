package com.globallogic.casino.model.entity.h2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CUSTOMERS")
@JsonIgnoreProperties("currentlyPlayedGame")
public class Customer extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(nullable = false)
    private BigDecimal currentBalance;
    @Column(nullable = false)
    private LocalDateTime lastTimePresent;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "current_game_id", referencedColumnName = "gameId")
    private Game currentlyPlayedGame;
}
