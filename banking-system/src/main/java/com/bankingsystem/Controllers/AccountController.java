package com.bankingsystem.Controllers;

import com.bankingsystem.Dtos.AccountDto;
import com.bankingsystem.Dtos.TransactionDto;
import com.bankingsystem.Entities.Account;
import com.bankingsystem.Entities.Transaction;
import com.bankingsystem.Mappers.AccountMapper;
import com.bankingsystem.Services.Interfaces.AccountService;
import com.bankingsystem.Services.Interfaces.TransactionService;
import com.bankingsystem.Services.TransactionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;
private final TransactionServiceImpl transactionService;

    public AccountController(AccountService accountService,
                             AccountMapper accountMapper, TransactionServiceImpl transactionService) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.transactionService = transactionService;
    }

    // GET Endpoint
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> allAccounts = accountService.getAccounts();
        return ResponseEntity.ok(allAccounts);
    }

    @PostMapping("/create-new-account/{id}")
    ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto, @PathVariable Long id) {
        //directrly it gets linked to a customer
        AccountDto account = accountMapper.toAccountDto(accountService.addAccount(id, accountDto));
        return ResponseEntity.ok(account);
    }
    @GetMapping("/{account_id}/transactions")
    ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable Long account_id) {
        List <TransactionDto> transactionsOfSpecificAcc = accountService.getTransactionsOfSpecificAcc(account_id);
        return ResponseEntity.ok(transactionsOfSpecificAcc);
    }


    @DeleteMapping("/delete-account/{account_id}")
    ResponseEntity<Void> deleteAccount(@PathVariable Long account_id) {
        accountService.deleteAccount(account_id);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/{account_id}/make-new-transaction")
    public ResponseEntity<TransactionDto> addTransaction(
            @PathVariable("account_id") Long accountId,
            @RequestBody TransactionDto transactionRequest) {

        TransactionDto createdTransaction = transactionService.saveTransaction(accountId, transactionRequest);
        return ResponseEntity.status(201).body(createdTransaction);
    }



}
