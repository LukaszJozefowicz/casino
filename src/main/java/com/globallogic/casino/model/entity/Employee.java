package com.globallogic.casino.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.globallogic.casino.model.Person;
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
public class Employee extends Person {
    @Id
    @GeneratedValue(generator = "employees_sequence")
    @Column(name = "employee_id")
    private Long employeeId;
    private BigDecimal salary;
    @Column(name = "work_position")
    @Enumerated(EnumType.STRING)
    private WorkPosition workPosition;
    @Column(name = "contract_start_date")
    private LocalDate contractStartDate;
    @Column(name = "contract_end_date")
    private LocalDate contractEndDate;
    @OneToOne
    @JsonManagedReference
    @JoinTable(name = "employee_game",
            joinColumns =
                    { @JoinColumn(name = "assigned_employee_id", referencedColumnName = "employee_id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "assigned_game_id", referencedColumnName = "game_id") })
    private Game assignedGame;
}
