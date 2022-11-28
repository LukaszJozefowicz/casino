package com.globallogic.casino.repository;

import com.globallogic.casino.model.entity.h2.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByCityAndStreetAndHouseNumberAndFlatNumberAndPostCode(
            String city,
            String street,
            Integer houseNumber,
            String flatNumber,
            String postCode);
}
