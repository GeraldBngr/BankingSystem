package com.bankingsystem.Services.Interfaces;

import com.bankingsystem.Dtos.CustomerDetailsDto;
import com.bankingsystem.Dtos.CustomerDto;
import com.bankingsystem.Entities.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    Customer createCustomer(Customer customer);
    void deleteCustomer(Long id);
     CustomerDetailsDto getFullDetailsOfCustomer(Long customerId);

}
