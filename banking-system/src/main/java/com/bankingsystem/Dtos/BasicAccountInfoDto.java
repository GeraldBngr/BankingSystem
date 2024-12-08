package com.bankingsystem.Dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BasicAccountInfoDto {
    private String iban;
    private double balance;
}
