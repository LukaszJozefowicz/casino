package com.globallogic.casino.controller;

import com.globallogic.casino.model.dto.h2.CustomerDto;
import com.globallogic.casino.model.dto.h2.CustomerToAddDto;
import com.globallogic.casino.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

    @PostMapping("/add")
    public ResponseEntity<CustomerToAddDto> addCustomer(@RequestBody CustomerToAddDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customerDto));
    }

    @DeleteMapping("/{customerId}/remove")
    public String removeCustomer(@PathVariable Long customerId) {
        return customerService.removeCustomer(customerId);
    }

    @SneakyThrows
    @PatchMapping("/{customerId}/changeCurrentBalance")
    public String changeCustomerCurrentBalance(@PathVariable Long customerId, @RequestParam BigDecimal newBalance) {
        return customerService.changeCurrentBalance(customerId, newBalance);
    }
}
