package com.healthpro.customer.controller;


import com.healthpro.customer.model.CustomerModel;
import com.healthpro.customer.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PutMapping(value = "/add")
    public CustomerModel registerNewCustomer(@RequestBody @Valid CustomerModel customerModel) {
        return customerService.register(customerModel);
    }

    @GetMapping(value = "/get/{id}")
    public CustomerModel getCustomer(@PathVariable long id) {
        return customerService.getCustomerById(id);
    }


    @DeleteMapping(value = "/delete/{id}")
    public String deleteCustomer(@PathVariable long id) {
        return customerService.deleteCustomerById(id);
    }

    @PostMapping(value = "/update/{id}")
    public CustomerModel updateCustomerById(@PathVariable @NotBlank Long id, @RequestBody CustomerModel customerModel) {
        customerModel.setCustomerId(id);
        return customerService.updateCustomerById(customerModel);
    }
}
