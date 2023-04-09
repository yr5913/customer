package com.healthpro.customer.service;

import com.healthpro.customer.model.CustomerModel;

public interface CustomerService {

    CustomerModel register(CustomerModel customerModel);

    CustomerModel getCustomerById(long customerId);

    String deleteCustomerById(long customerId);

    CustomerModel updateCustomerById(CustomerModel customerModel);
}
