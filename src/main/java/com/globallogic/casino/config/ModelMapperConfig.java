package com.globallogic.casino.config;

import com.globallogic.casino.model.dto.h2.CustomerDto;
import com.globallogic.casino.model.dto.h2.CustomerToAddDto;
import com.globallogic.casino.model.dto.h2.EmployeeDto;
import com.globallogic.casino.model.dto.h2.EmployeeToAddDto;
import com.globallogic.casino.model.entity.h2.Customer;
import com.globallogic.casino.model.entity.h2.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Employee.class, EmployeeDto.class)
                .addMapping(employee -> employee.getAssignedGame().getGameId(), EmployeeDto::setAssignedGameId)
                .addMapping(employee -> employee.getAssignedGame().getGameType(), EmployeeDto::setAssignedGameType)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(ctx -> ((Employee) ctx.getSource()).getFirstName() + " "
                                + ((Employee) ctx.getSource()).getLastName())
                                .map(source, destination.getFullName());
                    }
                });


        modelMapper.typeMap(Customer.class, CustomerDto.class)
                .addMapping(customer -> customer.getCurrentlyPlayedGame().getGameId(), CustomerDto::setCurrentlyPlayedGameId)
                .addMapping(customer -> customer.getCurrentlyPlayedGame().getGameType(), CustomerDto::setCurrentlyPlayedGameType)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(ctx -> ((Customer) ctx.getSource()).getFirstName() + " "
                                + ((Customer) ctx.getSource()).getLastName())
                                .map(source, destination.getFullName());
                    }
                });

        modelMapper.typeMap(EmployeeToAddDto.class, Employee.class)
                .addMappings(mapper -> mapper.skip(Employee::setAssignedGame))
                .addMappings(mapper -> mapper.skip(Employee::setEmployeeId));

        modelMapper.typeMap(CustomerToAddDto.class, Customer.class)
                .addMappings(mapper -> mapper.skip(Customer::setCustomerId))
                .addMappings(mapper -> mapper.skip(Customer::setCurrentlyPlayedGame));


        return modelMapper;
    }
}
