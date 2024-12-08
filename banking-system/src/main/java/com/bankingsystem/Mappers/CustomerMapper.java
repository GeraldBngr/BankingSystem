package com.bankingsystem.Mappers;

import com.bankingsystem.Dtos.AccountDto;
import com.bankingsystem.Dtos.CustomerDto;
import com.bankingsystem.Entities.Account;
import com.bankingsystem.Entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // Basic Customer Mapping
   @Mapping(target = "accounts",ignore = true)
   @Mapping(target = "taxNumber", ignore = true)
   @Mapping(target = "phone", ignore = true)
    CustomerDto toBasicDto(Customer customer);

    // Detailed Customer Mapping
    CustomerDto toDetailedDto(Customer customer);




}
