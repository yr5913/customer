package com.healthpro.customer.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long customerId;
    String firstName;

    String middleName;
    String lastName;
    String email;
    String streetLine1;
    String streetLine2;
    String city;
    String state;
    int zip;
    String phone;
    Date dateOfBirth;
    String sex;
}
