package com.jedosa.junglim.account.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String zipcode;
    private String address;
    private String detailedAddress;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.name = account.getName();
        this.phone = account.getPhone();
        this.zipcode = account.getAddress().getZipcode();
        this.address = account.getAddress().getAddress();
        this.detailedAddress = account.getAddress().getDetailedAddress();
    }

    public String getFullAddress() {
        return zipcode + " " + address + " " + detailedAddress;
    }
}
