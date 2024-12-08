package com.bankingsystem.Mappers;

import com.bankingsystem.Dtos.AccountDto;
import com.bankingsystem.Dtos.BasicAccountInfoDto;
import com.bankingsystem.Entities.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    // Map Single Account to AccountDto
    AccountDto toAccountDto(Account account);

    BasicAccountInfoDto toBasicAccountInfoDto(Account account);
    // Map List of Accounts to List of AccountDtos
    List<AccountDto> toAccountDtosList(List<Account> accounts);

    Account toAccount(AccountDto accountDto);
}
