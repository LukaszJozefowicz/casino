package com.globallogic.casino.model.dto.h2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private String city;
    private String street;
    private Integer houseNumber;
    private String flatNumber;
    private String postCode;
}
