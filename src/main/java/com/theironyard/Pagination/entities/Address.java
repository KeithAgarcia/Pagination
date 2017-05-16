package com.theironyard.Pagination.entities;

import javax.persistence.*;

/**
 * Created by Keith on 5/16/17.
 */

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String street;

    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    String state;

    @Column(nullable = false)
    int zipCode;

    public Address() {
    }

    public Address(String street, String city, String state, int zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
