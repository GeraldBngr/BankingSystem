package com.bankingsystem.Services.Interfaces;

import com.bankingsystem.Dtos.AccountDto;
import com.bankingsystem.Dtos.TransactionDto;
import com.bankingsystem.Entities.Account;

import java.util.List;


public interface AccountService {


    List<AccountDto> getAccounts();

    Account addAccount(Long customer_id, AccountDto accountDto);

    void deleteAccount(Long account_id);

    List<TransactionDto> getTransactionsOfSpecificAcc(Long account_id);



}
