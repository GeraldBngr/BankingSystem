package com.bankingsystem.Services.Interfaces;

import com.bankingsystem.Dtos.CustomerDto;
import com.bankingsystem.Entities.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    Customer createCustomer(Customer customer);
    void deleteCustomer(Long id);

}
