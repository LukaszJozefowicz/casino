package com.globallogic.casino.model.entity.h2;

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
    @Column(nullable = false)
    private String universalId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId", nullable = false)
    private Address address;
}
