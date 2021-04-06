package com.jedosa.junglim.account.domain;

import com.jedosa.junglim.article.domain.Pagination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountsDto {
    List<AccountDto> accountDtos;
    Pagination pagination;

    public AccountsDto(List<AccountDto> accountDtos, Pagination pagination) {
        this.accountDtos = accountDtos;
        this.pagination = pagination;
    }
}