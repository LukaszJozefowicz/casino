package com.globallogic.casino.service.impl;

import com.globallogic.casino.model.entity.h2.Address;
import com.globallogic.casino.model.entity.h2.Customer;
import com.globallogic.casino.model.entity.h2.Employee;
import com.globallogic.casino.repository.AddressRepository;
import com.globallogic.casino.repository.CustomerRepository;
import com.globallogic.casino.repository.EmployeeRepository;
import com.globallogic.casino.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    @Override
    public String removeOrphanedAddresses() {
        List<Long> removedAddressIds = new LinkedList<>();
        List<Address> addresses = addressRepository.findAll();

        for (Address address : addresses) {
            Long addressId = address.getAddressId();
            List<Customer> customersByAddressId = customerRepository
                    .findAllByAddress_AddressId(addressId);
            List<Employee> employeesByAddressId = employeeRepository
                    .findAllByAddress_AddressId(addressId);

            if (customersByAddressId.isEmpty() && employeesByAddressId.isEmpty()) {
                addressRepository.delete(address);
                removedAddressIds.add(addressId);
            }
        }

        return removedAddressIds.isEmpty()
                ? "No addresses to remove"
                : "Removed orphaned addresses with ids: " + removedAddressIds;
    }
}
