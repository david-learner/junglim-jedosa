package com.jedosa.junglim.account.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {
    private Long id;
    private Boolean deleted;
    private String email;
    private String password;
    private String name;
    private String phone;
    // Address Object Start
    private String zipcode;
    private String address;
    private String detailedAddress;
    // Address Object End
    private Boolean siteUsageAgreement;
    private Boolean personalInfoAgreement;

    public Account toAccount () {
        Address embeddedAddress = new Address(zipcode, address, detailedAddress);
        return new Account(email, password, name, phone, embeddedAddress, siteUsageAgreement, personalInfoAgreement, Grade.NORMAL);
    }
}
