package com.globallogic.casino.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globallogic.casino.model.enums.WorkPosition;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto {
    private Long employeeId;
    private BigDecimal salary;
    private WorkPosition workPosition;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate contractStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate contractEndDate;
    private GameDto assignedGame;
}
