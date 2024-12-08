package com.bankingsystem.Services.Interfaces;

import com.bankingsystem.Dtos.TransactionDto;
import com.bankingsystem.Entities.Transaction;

public interface TransactionService {

    TransactionDto saveTransaction(Long account_id,TransactionDto transactionDto);
}
