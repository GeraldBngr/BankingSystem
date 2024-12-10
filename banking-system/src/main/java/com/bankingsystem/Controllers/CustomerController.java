package com.bankingsystem.Controllers;

import com.bankingsystem.Dtos.AccountDto;
import com.bankingsystem.Dtos.BasicAccountInfoDto;
import com.bankingsystem.Dtos.CustomerDetailsDto;
import com.bankingsystem.Dtos.CustomerDto;
import com.bankingsystem.Entities.Customer;
import com.bankingsystem.Services.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    public CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }


    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long customerId) {
        CustomerDto customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping("create-new-customer")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @DeleteMapping("delete-customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update-customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long customerId, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PatchMapping("update-customer-partially/{id}")
    public ResponseEntity<Customer> updateCustomerPartially(@PathVariable("id") Long customerId, @RequestBody Map<String, Object> changes) {
        Customer updatedCustomer = customerService.updateCustomerPartially(customerId, changes);
        return ResponseEntity.ok(updatedCustomer);
    }

    //retrieve the accounts of a sepcific customer
    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<BasicAccountInfoDto>> getAccountsByCustomerId(@PathVariable("id") Long customerId) {

        List<BasicAccountInfoDto> accountsOfACustomer = customerService.getCustomerAccounts(customerId);
        return ResponseEntity.ok(accountsOfACustomer);
    }

    @GetMapping("/{customer_id}/get-full-details")
    public ResponseEntity<CustomerDetailsDto> getFullDetails(@PathVariable Long customer_id) {
       CustomerDetailsDto customer_details = customerService.getFullDetailsOfCustomer(customer_id);
        return ResponseEntity.ok(customer_details);

    }

}