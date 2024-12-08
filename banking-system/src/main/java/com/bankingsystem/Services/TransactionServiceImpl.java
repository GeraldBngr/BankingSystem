package com.bankingsystem.Services;

import com.bankingsystem.Dtos.TransactionDto;
import com.bankingsystem.Entities.Account;
import com.bankingsystem.Entities.Transaction;
import com.bankingsystem.Entities.TransactionType;
import com.bankingsystem.Mappers.TransactionMapper;
import com.bankingsystem.Repositories.AccountRepository;
import com.bankingsystem.Repositories.TransactionRepository;
import com.bankingsystem.Services.Interfaces.TransactionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
   private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository,
                                  TransactionMapper transactionMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDto saveTransaction(Long account_id, TransactionDto newTransactionRequest) {
        Account account = accountRepository.findById(account_id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Handle Enum-based Transaction Type
        TransactionType transactionType = newTransactionRequest.getType();

        if (transactionType == TransactionType.DEPOSIT) {
            account.setBalance(account.getBalance() + newTransactionRequest.getAmount());
        } else if (transactionType == TransactionType.WITHDRAW) {
            if (account.getBalance() < newTransactionRequest.getAmount()) {
                throw new IllegalArgumentException("Insufficient balance for withdrawal.");
            }
            account.setBalance(account.getBalance() - newTransactionRequest.getAmount());
        } else {
            throw new IllegalArgumentException("Invalid transaction type.");
        }

        // Create and Save Transaction
        Transaction transactionToSave = new Transaction();
        transactionToSave.setAccount(account);
        transactionToSave.setType(transactionType);
        transactionToSave.setAmount(newTransactionRequest.getAmount());
        transactionToSave.setTransactionDate(java.time.LocalDateTime.now());

        transactionRepository.save(transactionToSave);
        accountRepository.save(account);

        return transactionMapper.toTransactionDto(transactionToSave);
    }


}
