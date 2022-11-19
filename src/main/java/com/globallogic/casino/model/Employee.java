package com.globallogic.casino.model;

import com.globallogic.casino.model.enums.WorkPosition;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person {
    @Id
    private final UUID employeeId = UUID.randomUUID();
    private BigDecimal salary;
    private WorkPosition workPosition;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
}
