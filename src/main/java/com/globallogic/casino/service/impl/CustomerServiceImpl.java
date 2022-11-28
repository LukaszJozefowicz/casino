package com.globallogic.casino.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.casino.exception.EntityNotFoundException;
import com.globallogic.casino.model.dto.h2.CustomerDto;
import com.globallogic.casino.model.dto.h2.CustomerToAddDto;
import com.globallogic.casino.model.entity.h2.Customer;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.repository.AddressRepository;
import com.globallogic.casino.repository.CustomerRepository;
import com.globallogic.casino.repository.GameRepository;
import com.globallogic.casino.service.CustomerService;
import com.globallogic.casino.util.PersonUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final GameRepository gameRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;


    @Override
    public CustomerDto joinGame(Long customerId, Long gameId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));
        customer.setCurrentlyPlayedGame(game);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDto.class);
    }

    @Override
    public CustomerDto leaveGame(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));

        if (nonNull(customer.getCurrentlyPlayedGame())) {
            customer.setCurrentlyPlayedGame(null);
            customer = customerRepository.save(customer);
        }
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public CustomerToAddDto addCustomer(CustomerToAddDto customerDto) {
        Customer toSave = modelMapper.map(customerDto, Customer.class);
        PersonUtil.setAddressIfAlreadyExists(toSave, addressRepository);
        customerRepository.save(toSave);
        return customerDto;
    }

    @Override
    public String removeCustomer(Long customerId) {
        return PersonUtil.removePerson(customerId, customerRepository);
    }

    @SneakyThrows
    @Override
    public String changeCurrentBalance(Long customerId, BigDecimal newCurrentBalance) {
        return PersonUtil.patchPerson(
                customerId,
                Customer.class,
                customerRepository,
                "currentBalance",
                objectMapper.getNodeFactory().numberNode(newCurrentBalance));
    }
}
