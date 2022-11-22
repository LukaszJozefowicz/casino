package com.globallogic.casino.service.impl;

import com.globallogic.casino.exception.EntityNotFoundException;
import com.globallogic.casino.model.entity.Employee;
import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.model.dto.EmployeeDto;
import com.globallogic.casino.repository.EmployeeRepository;
import com.globallogic.casino.repository.GameRepository;
import com.globallogic.casino.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeDto assignToGame(Long employeeId, Long gameId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException(Employee.class, employeeId));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));
        employee.setAssignedGame(game);
        Employee savedEmployee =  employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }
}
