package com.bankingsystem.Mappers;

import com.bankingsystem.Dtos.TransactionDto;
import com.bankingsystem.Entities.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    // Map Entity to DTO
    TransactionDto toTransactionDto(Transaction transaction);

    // Map List of Transactions
    List<TransactionDto> toTransactionDtoList(List<Transaction> transactions);
}
