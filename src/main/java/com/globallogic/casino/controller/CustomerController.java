package com.globallogic.casino.controller;

import com.globallogic.casino.model.dto.CustomerDto;
import com.globallogic.casino.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PutMapping("/{customerId}/joinGame/{gameId}")
    public CustomerDto joinGame(@PathVariable Long customerId, @PathVariable Long gameId) {
        return customerService.joinGame(customerId, gameId);
    }

    @PutMapping("/{customerId}/leaveGame")
    public CustomerDto leaveGame(@PathVariable Long customerId) {
        return customerService.leaveGame(customerId);
    }
}
