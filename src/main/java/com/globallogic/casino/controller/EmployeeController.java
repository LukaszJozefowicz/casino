package com.globallogic.casino.controller;

import com.globallogic.casino.model.dto.h2.EmployeeDto;
import com.globallogic.casino.model.dto.h2.EmployeeToAddDto;
import com.globallogic.casino.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PutMapping("/{employeeId}/assignToGame/{gameId}")
    public EmployeeDto assignToGame(@PathVariable Long employeeId, @PathVariable Long gameId) {
        return employeeService.assignToGame(employeeId, gameId);
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeToAddDto> addEmployee(@RequestBody EmployeeToAddDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employeeDto));
    }

    @DeleteMapping("/{employeeId}/remove")
    public String removeEmployee(@PathVariable Long employeeId) {
        return employeeService.removeEmployee(employeeId);
    }

    @SneakyThrows
    @PatchMapping("/{employeeId}/changeSalary")
    public String changeEmployeeSalary(@PathVariable Long employeeId, @RequestParam BigDecimal newAmount) {
        return employeeService.changeSalary(employeeId, newAmount);
    }

    @SneakyThrows
    @PatchMapping("/{employeeId}/extendContract")
    public String extendEmployeeContract(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate newEndDate) {
        return employeeService.extendEmploymentContract(employeeId, newEndDate);
    }
}
