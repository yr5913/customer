package com.healthpro.customer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@NotNull
@Data
public class CustomerModel {


    long customerId;
    @NotBlank(message = "cannot be blank")
    String firstName;

    String middleName;

    @NotBlank(message = "cannot be blank")
    String lastName;

    @NotBlank(message = "cannot be blank")
    String email;

    @NotBlank(message = "cannot be blank")
    String streetLine1;

    String streetLine2;

    @NotBlank(message = "cannot be blank")
    String city;

    @NotBlank(message = "cannot be blank")
    String state;

    int zip;

    @NotBlank(message = "cannot be blank")
    String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date dateOfBirth;

    @NotBlank(message = "cannot be blank")
    String sex;


}
