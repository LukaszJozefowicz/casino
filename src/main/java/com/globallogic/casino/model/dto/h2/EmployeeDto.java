package com.globallogic.casino.model.dto.h2;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globallogic.casino.model.enums.GameType;
import com.globallogic.casino.model.enums.WorkPosition;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto {
    private Long employeeId;
    private String fullName;
    private BigDecimal salary;
    private WorkPosition workPosition;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate contractStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate contractEndDate;
    private Long assignedGameId;
    private GameType assignedGameType;
}
