package com.globallogic.casino.service;

import com.globallogic.casino.model.dto.CustomerDto;

public interface CustomerService {
    CustomerDto joinGame(Long customerId, Long gameId);
    CustomerDto leaveGame(Long customerId);
}
