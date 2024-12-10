package com.bankingsystem.Services;

import com.bankingsystem.Dtos.BasicAccountInfoDto;
import com.bankingsystem.Dtos.CustomerDetailsDto;
import com.bankingsystem.Dtos.CustomerDto;
import com.bankingsystem.Dtos.TransactionDto;
import com.bankingsystem.Entities.Customer;
import com.bankingsystem.Mappers.AccountMapper;
import com.bankingsystem.Mappers.CustomerMapper;
import com.bankingsystem.Mappers.TransactionMapper;
import com.bankingsystem.Repositories.AccountRepository;
import com.bankingsystem.Repositories.CustomerRepository;
import com.bankingsystem.Services.Interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, AccountRepository accountRepository,
                               AccountMapper accountMapper,
                               TransactionMapper transactionMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
    }

    // Get All Customers
    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toBasicDto).toList();
    }


    @Override
    public CustomerDto getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).map(customerMapper::toDetailedDto).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public Customer updateCustomer(Long customerId, Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer.isPresent()) {
            Customer updatedCustomer = existingCustomer.get();
            updatedCustomer.setFirstName(customer.getFirstName());
            updatedCustomer.setLastName(customer.getLastName());
            updatedCustomer.setMail(customer.getMail());
            updatedCustomer.setPhone(customer.getPhone());
            updatedCustomer.setTaxNumber(customer.getTaxNumber());
            return customerRepository.save(updatedCustomer);
        } else {
            throw new RuntimeException("Customer not found");
        }


    }
    public Customer updateCustomerPartially(Long customerId, Map <String,Object> changes) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        changes.forEach((key, value) -> {
            switch (key) {
                case "firstName" -> customer.setFirstName((String) value);
                case "lastName" -> customer.setLastName((String) value);
                case "mail" -> customer.setMail((String) value);
                case "taxNumber" -> customer.setTaxNumber((String) value);
                case "phone" -> customer.setPhone((String) value);
                default -> throw new IllegalArgumentException("Field " + key + " is not updatable.");
            }
        });
        return customerRepository.save(customer);
    }
    public List <BasicAccountInfoDto> getCustomerAccounts(Long customerId) {
        Customer searchedCustomer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        return accountRepository.findByCustomer(searchedCustomer).stream().map(accountMapper::toBasicAccountInfoDto).collect(Collectors.toList());
    }

    public CustomerDetailsDto getFullDetailsOfCustomer(Long customerId) {
        Customer customer=customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        CustomerDetailsDto customerDetails=new CustomerDetailsDto();
        customerDetails.setFullName(customer.getFirstName() + " " + customer.getLastName());
        customerDetails.setPhoneNumber(customer.getPhone());
        Map <String,List<TransactionDto>> accountsWithTransactions =  new HashMap<>();
        customer.getAccounts().forEach(account -> {
            List <TransactionDto> transactions= account.getTransactions()
                    .stream().map(transactionMapper::toTransactionDto)
                    .toList();

            accountsWithTransactions.put(account.getIban(), transactions);

        });
customerDetails.setAccounts(accountsWithTransactions);
    return customerDetails;
    }

}

