package com.healthpro.customer.controller;

import com.healthpro.customer.model.TestInRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {


    @PutMapping(value = "/put")
    public void putSomething(@RequestBody @Valid TestInRequest testInRequest) {
        System.out.println("gg");
    }
}
