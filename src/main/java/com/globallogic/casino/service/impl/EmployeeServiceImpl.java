package com.globallogic.casino.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.casino.exception.CannotUpdateEntityException;
import com.globallogic.casino.exception.EntityNotFoundException;
import com.globallogic.casino.model.dto.h2.EmployeeDto;
import com.globallogic.casino.model.dto.h2.EmployeeToAddDto;
import com.globallogic.casino.model.entity.h2.Employee;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.repository.AddressRepository;
import com.globallogic.casino.repository.EmployeeRepository;
import com.globallogic.casino.repository.GameRepository;
import com.globallogic.casino.service.EmployeeService;
import com.globallogic.casino.util.PersonUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static com.globallogic.casino.exception.CannotUpdateEntityException.EMPLOYEE_ALREADY_ASSIGNED_MSG;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final GameRepository gameRepository;
    private final AddressRepository addressRepository;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeDto assignToGame(Long employeeId, Long gameId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException(Employee.class, employeeId));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));

        assignEmployeeToGameIfPossible(employee, game);

        Employee savedEmployee =  employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeToAddDto addEmployee(EmployeeToAddDto employeeDto) {
        Employee toSave = modelMapper.map(employeeDto, Employee.class);
        PersonUtil.setAddressIfAlreadyExists(toSave, addressRepository);
        employeeRepository.save(toSave);
        return employeeDto;
    }

    @Override
    public String removeEmployee(Long employeeId) {
        return PersonUtil.removePerson(employeeId, employeeRepository);
    }

    @SneakyThrows
    @Override
    public String changeSalary(Long employeeId, BigDecimal newAmount) {
        return PersonUtil.patchPerson(
                employeeId,
                Employee.class,
                employeeRepository,
                "salary",
                objectMapper.getNodeFactory().numberNode(newAmount));
    }

    @SneakyThrows
    @Override
    public String extendEmploymentContract(Long employeeId, LocalDate newContractEndDate) {
        return PersonUtil.patchPerson(
                employeeId,
                Employee.class,
                employeeRepository,
                "contractEndDate",
                objectMapper.getNodeFactory().pojoNode(newContractEndDate));
    }

    private void assignEmployeeToGameIfPossible(Employee employee, Game game) {
        Optional.ofNullable(game.getAssignedEmployee())
                .ifPresent(e -> {
                    throw new CannotUpdateEntityException(game.getGameId(), EMPLOYEE_ALREADY_ASSIGNED_MSG);
                });
        Optional.ofNullable(employee.getAssignedGame())
                .ifPresent(e -> {
                    throw new CannotUpdateEntityException(game.getGameId(), EMPLOYEE_ALREADY_ASSIGNED_MSG);
                });
        employee.setAssignedGame(game);

    }
}
