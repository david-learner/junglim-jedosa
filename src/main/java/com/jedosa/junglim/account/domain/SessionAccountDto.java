package com.jedosa.junglim.account.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SessionAccountDto {

    public static SessionAccountDto GUEST = new SessionAccountDto(Account.GUEST);

    private Long id;
    private String email;
    private String name;
    private Grade grade;

    public SessionAccountDto(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.name = account.getName();
        this.grade = account.getGrade();
    }

    public boolean isAdmin() {
        return grade == Grade.ADMIN;
    }

    public boolean isGuest() {
        return grade == Grade.GUEST;
    }
}
