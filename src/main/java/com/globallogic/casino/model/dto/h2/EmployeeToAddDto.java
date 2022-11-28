package com.globallogic.casino.model.dto.h2;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globallogic.casino.model.enums.WorkPosition;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeToAddDto {
    private String universalId;
    private String firstName;
    private String lastName;
    private AddressDto address;
    private BigDecimal salary;
    private WorkPosition workPosition;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate contractStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate contractEndDate;
}
