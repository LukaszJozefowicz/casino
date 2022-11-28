package com.globallogic.casino.model.entity.h2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.globallogic.casino.model.enums.WorkPosition;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EMPLOYEES")
@JsonIgnoreProperties("assignedGame")
public class Employee extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    private WorkPosition workPosition;
    @Column(nullable = false)
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    @OneToOne
    @JoinColumn(name = "assigned_game_id", referencedColumnName = "gameId")
    @JsonManagedReference(value = "emp-game-ref")
    private Game assignedGame;
}
