package com.globallogic.casino.service.impl;

import com.globallogic.casino.exception.EntityNotFoundException;
import com.globallogic.casino.model.dto.CustomerDto;
import com.globallogic.casino.model.entity.Customer;
import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.repository.CustomerRepository;
import com.globallogic.casino.repository.GameRepository;
import com.globallogic.casino.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;


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
}
