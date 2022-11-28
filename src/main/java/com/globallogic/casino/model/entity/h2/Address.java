package com.globallogic.casino.model.entity.h2;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column(nullable = false)
    private String city;
    private String street;
    @Column(nullable = false)
    private Integer houseNumber;
    private String flatNumber;
    @Column(nullable = false)
    private String postCode;
}
