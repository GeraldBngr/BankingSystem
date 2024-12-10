package com.bankingsystem.Dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



import java.util.List;
import java.util.Map;
@Getter
@Setter
@RequiredArgsConstructor
public class CustomerDetailsDto {
    private String fullName;
    private String phoneNumber;
    private Map<String, List<TransactionDto>> accounts; //key:IBAN, Values:Transactions that are made by account with this iban

}

