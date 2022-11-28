package com.globallogic.casino.service;

import com.globallogic.casino.model.dto.h2.CustomerDto;
import com.globallogic.casino.model.dto.h2.CustomerToAddDto;

import java.math.BigDecimal;

public interface CustomerService {
    CustomerDto joinGame(Long customerId, Long gameId);
    CustomerDto leaveGame(Long customerId);
    CustomerToAddDto addCustomer(CustomerToAddDto customerDto);
    String removeCustomer(Long customerId);
    String changeCurrentBalance(Long customerId, BigDecimal newCurrentBalance);
}
