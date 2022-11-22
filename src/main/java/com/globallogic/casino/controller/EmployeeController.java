package com.globallogic.casino.controller;

import com.globallogic.casino.model.dto.EmployeeDto;
import com.globallogic.casino.model.dto.GameDto;
import com.globallogic.casino.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/{employeeId}/assignToGame/{gameId}")
    public EmployeeDto assign(@PathVariable Long employeeId, @PathVariable Long gameId) {
        return employeeService.assignToGame(employeeId, gameId);
    }
}
