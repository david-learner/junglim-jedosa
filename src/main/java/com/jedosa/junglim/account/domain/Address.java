package com.jedosa.junglim.account.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class Address {
    private String zipcode;
    private String address;
    private String detailedAddress;

    public Address(String zipcode, String address, String detailedAddress) {
        this.zipcode = zipcode;
        this.address = address;
        this.detailedAddress = detailedAddress;
    }
}
