package com.healthpro.customer.service.impl;

import com.healthpro.customer.entity.Customer;
import com.healthpro.customer.model.CustomerModel;
import com.healthpro.customer.repository.CustomerRepository;
import com.healthpro.customer.service.CustomerService;
import com.healthpro.customer.util.BeanUtilsCustom;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public CustomerModel register(CustomerModel customerModel) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerModel, customer);
        customer = customerRepository.save(customer);
        customerModel.setCustomerId(customer.getCustomerId());
        return customerModel;
    }

    @Override
    public CustomerModel getCustomerById(long customerId) {
        CustomerModel customerModel = new CustomerModel();
        BeanUtils.copyProperties(customerRepository.getReferenceById(customerId), customerModel);
        return customerModel;
    }

    @Override
    public String deleteCustomerById(long customerId) {
        customerRepository.deleteById(customerId);
        return "Customer with id  " + customerId + " is deleted";
    }

    @Override
    public CustomerModel updateCustomerById(CustomerModel customerModel) {
        Customer customer = customerRepository.getReferenceById(customerModel.getCustomerId());
        BeanUtilsCustom.copyPropertiesIfNotNull(customerModel, customer);
        customer = customerRepository.save(customer);
        BeanUtilsCustom.copyPropertiesIfNotNull(customer, customerModel);
        return customerModel;
    }


}
