package com.bankingsystem.Dtos;

import com.bankingsystem.Entities.Transaction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountDto {
    private Long id;
    private String iban;
    private double balance;
    private List<TransactionDto> transactions;

}
