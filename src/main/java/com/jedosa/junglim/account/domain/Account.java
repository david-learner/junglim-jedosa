package com.jedosa.junglim.account.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private LocalDateTime deletedDateTime;
    private Boolean deleted;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String phone;
    @Embedded
    private Address address;
    private Boolean siteUsageAgreement;
    private Boolean personalInfoAgreement;
    @Enumerated(EnumType.STRING)
    private Grade grade;

    public Account(String email, String password, String name, String phone, Address address, Boolean siteUsageAgreement, Boolean personalInfoAgreement) {
        this.createdDateTime = LocalDateTime.now();
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.siteUsageAgreement = siteUsageAgreement;
        this.personalInfoAgreement = personalInfoAgreement;
        this.grade = Grade.NORMAL;
    }

    public void upgradeToAdmin() {
        if (grade == Grade.NORMAL) {
            grade = Grade.ADMIN;
            return;
        }
        throw new IllegalArgumentException("이미 관리자 등급입니다");
    }
}
