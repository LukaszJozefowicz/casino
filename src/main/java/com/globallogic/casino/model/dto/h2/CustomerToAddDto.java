package com.globallogic.casino.model.dto.h2;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerToAddDto {
    private String universalId;
    private String firstName;
    private String lastName;
    private AddressDto address;
    private BigDecimal currentBalance = BigDecimal.ZERO;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTimePresent = LocalDateTime.now().withNano(0);
}
