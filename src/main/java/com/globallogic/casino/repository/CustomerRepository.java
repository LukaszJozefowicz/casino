package com.globallogic.casino.repository;

import com.globallogic.casino.model.entity.h2.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByAddress_AddressId(Long addressId);
}
