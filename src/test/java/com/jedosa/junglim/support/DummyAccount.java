package com.jedosa.junglim.support;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.account.domain.Address;
import com.jedosa.junglim.account.domain.Grade;
import com.jedosa.junglim.account.domain.SessionAccountDto;

public class DummyAccount {

    public static Account admin;
    public static Account normalFirst;
    public static Account normalSecond;
    public static SessionAccountDto sessionAdmin;
    public static SessionAccountDto sessionNormalFirst;

    static {
        admin = new Account(
                "testadmin@gmail.com", "11111111", "관리자",
                "01012345678", new Address("12345", "서울시 강남구 역삼동 12", "2층"),
                true, true, Grade.NORMAL);
        admin.upgradeToAdmin();

        normalFirst = new Account(
                "testnormal1@gmail.com", "11111111", "김일반",
                "01012345678", new Address("54321", "서울시 용산구 청파동 24", "3층"),
                true, true, Grade.NORMAL);

        normalSecond = new Account(
                "testnormal2@gmail.com", "22222222", "김일반",
                "01012345678", new Address("54321", "서울시 용산구 청파동 24", "3층"),
                true, true, Grade.NORMAL);

        sessionAdmin = new SessionAccountDto(admin);
        sessionNormalFirst = new SessionAccountDto(normalFirst);
    }
}
