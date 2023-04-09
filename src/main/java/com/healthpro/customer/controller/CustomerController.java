package com.healthpro.customer.controller;


import com.healthpro.customer.model.CustomerModel;
import com.healthpro.customer.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PutMapping(value = "/register")
    public CustomerModel registerNewCustomer(@RequestBody @Valid CustomerModel customerModel) {
        return customerService.register(customerModel);
    }

    @GetMapping(value = "/getCustomerById")
    public CustomerModel getCustomer(@RequestParam long id) {
        return customerService.getCustomerById(id);
    }


    @DeleteMapping(value = "/deleteCustomerById")
    public String deleteCustomer(@RequestParam long id) {
        return customerService.deleteCustomerById(id);
    }

    @PostMapping(value = "/updateCustomerById")
    public CustomerModel updateCustomerById(@RequestParam @NotBlank Long id, @RequestBody CustomerModel customerModel) {
        customerModel.setCustomerId(id);
        return customerService.updateCustomerById(customerModel);
    }
}
