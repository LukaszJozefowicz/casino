package com.globallogic.casino.service;

import com.globallogic.casino.model.dto.h2.EmployeeDto;
import com.globallogic.casino.model.dto.h2.EmployeeToAddDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface EmployeeService {
    EmployeeDto assignToGame(Long employeeId, Long gameId);
    EmployeeToAddDto addEmployee(EmployeeToAddDto employeeDto);
    String removeEmployee(Long employeeId);
    String changeSalary(Long employeeId, BigDecimal newAmount);
    String extendEmploymentContract(Long employeeId, LocalDate newContractEndDate);
}
