package com.bankingsystem.Dtos;

import com.bankingsystem.Entities.TransactionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class TransactionDto {
    private Long id;
    private TransactionType type;
    private Double amount;
    private LocalDateTime transactionDate;

}
