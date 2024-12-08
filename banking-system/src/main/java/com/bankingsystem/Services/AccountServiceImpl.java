package com.bankingsystem.Services;

import com.bankingsystem.Dtos.AccountDto;
import com.bankingsystem.Dtos.TransactionDto;
import com.bankingsystem.Entities.Account;
import com.bankingsystem.Entities.Customer;
import com.bankingsystem.Mappers.AccountMapper;
import com.bankingsystem.Mappers.TransactionMapper;
import com.bankingsystem.Repositories.AccountRepository;
import com.bankingsystem.Repositories.CustomerRepository;
import com.bankingsystem.Services.Interfaces.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;
    private final CustomerServiceImpl customerServiceImpl;
    private final CustomerRepository customerRepository;
    private final TransactionMapper transactionMapper;

    public AccountServiceImpl(AccountRepository accountRepository , AccountMapper accountMapper, CustomerServiceImpl customerServiceImpl, CustomerRepository customerRepository,
                              TransactionMapper transactionMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.customerServiceImpl = customerServiceImpl;
        this.customerRepository = customerRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public List<AccountDto> getAccounts() {
        return accountMapper.toAccountDtosList(accountRepository.findAll());
    }

    @Override
    public Account  addAccount( Long customer_id,AccountDto accountDto) {
        Customer customer= customerRepository.findById(customer_id).orElseThrow(()-> new RuntimeException("Customer not found"));
        Account newAccount = accountMapper.toAccount(accountDto);
        newAccount.setCustomer(customer);
         return accountRepository.save(newAccount);
    }


    @Override
    public void deleteAccount(Long account_id) {
        Account account = accountRepository.findById(account_id).orElseThrow(()-> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }

    @Override
    public List<TransactionDto> getTransactionsOfSpecificAcc(Long account_id) {
        Account searchingAccount = accountRepository.findById(account_id).orElseThrow(()-> new RuntimeException("Account not found"));
        return searchingAccount.getTransactions().stream().map(transactionMapper::toTransactionDto).collect(Collectors.toList());
    }
}
