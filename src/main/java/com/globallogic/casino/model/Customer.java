package com.globallogic.casino.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer extends Person {
    @Id
    private final UUID customerId = UUID.randomUUID();
    private BigDecimal currentBalanceInTokens;
    private LocalDateTime lastTimePresent;
}
