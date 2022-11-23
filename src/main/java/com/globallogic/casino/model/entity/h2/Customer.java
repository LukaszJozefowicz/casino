package com.globallogic.casino.model.entity.h2;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.globallogic.casino.model.Person;
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
public class Customer extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "current_balance")
    private BigDecimal currentBalance;
    @Column(name = "last_time_present")
    private LocalDateTime lastTimePresent;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "current_game_id", referencedColumnName = "game_id")
    private Game currentlyPlayedGame;
}
