package com.globallogic.casino.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.globallogic.casino.model.dto.CustomerDto;
import com.globallogic.casino.model.dto.EmployeeDto;
import com.globallogic.casino.model.entity.h2.Customer;
import com.globallogic.casino.model.entity.h2.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Employee.class, EmployeeDto.class)
                .addMapping(employee -> employee.getAssignedGame().getGameId(), EmployeeDto::setAssignedGameId)
                .addMapping(employee -> employee.getAssignedGame().getGameType(), EmployeeDto::setAssignedGameType);
        modelMapper.typeMap(Customer.class, CustomerDto.class)
                .addMapping(customer -> customer.getCurrentlyPlayedGame().getGameId(), CustomerDto::setCurrentlyPlayedGameId)
                .addMapping(customer -> customer.getCurrentlyPlayedGame().getGameType(), CustomerDto::setCurrentlyPlayedGameType);
        return modelMapper;
    }
}
