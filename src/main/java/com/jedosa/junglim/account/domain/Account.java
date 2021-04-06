package com.jedosa.junglim.account.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Account {

    private static final Logger log =  LoggerFactory.getLogger(Account.class);
    public static final Account GUEST = ofGuest();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="account_id")
    private Long id;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private LocalDateTime deletedDateTime;
    @Column(columnDefinition = "boolean default false")
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

    public Account(String email, String password, String name, String phone, Address address, Boolean siteUsageAgreement, Boolean personalInfoAgreement, Grade grade) {
        this.createdDateTime = LocalDateTime.now();
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.siteUsageAgreement = siteUsageAgreement;
        this.personalInfoAgreement = personalInfoAgreement;
        this.grade = grade;
    }

    private static Account ofGuest() {
        return new Account(null, null, null, null, null, null, null, Grade.GUEST);
    }

    public void upgradeToAdmin() {
        if (grade == Grade.NORMAL) {
            grade = Grade.ADMIN;
            return;
        }
        throw new IllegalArgumentException("이미 관리자 등급입니다");
    }

    public boolean isAdmin() {
        return getGrade() == Grade.ADMIN;
    }

    public boolean isGuest() {
        return getGrade() == Grade.GUEST;
    }

    public boolean isDeleted() {
        return getDeleted();
    }
}
