package com.globallogic.casino.service;

import com.globallogic.casino.model.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto assignToGame(Long employeeId, Long gameId);
}
