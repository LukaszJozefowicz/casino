package com.globallogic.casino.model;

import com.globallogic.casino.model.entity.h2.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Person {
    @Column(name = "universal_id", nullable = false)
    private String universalId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "addressId", nullable = false)
    private Address addressId;
}
