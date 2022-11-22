package com.globallogic.casino.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ADDRESSES")
public class Address {
    @Id
    private Long addressId;
    private String city;
    private String street;
    private Integer houseNumber;
    private String flatNumber;
    private String postCode;
}
