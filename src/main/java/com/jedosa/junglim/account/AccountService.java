package com.jedosa.junglim.account;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.account.domain.LoginDto;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.account.domain.SignUpDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountService {

    private static final Logger log =  LoggerFactory.getLogger(AccountService.class);
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void signUp(Account account) throws IllegalArgumentException {
        if(isDuplicated(account.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다");
        }
        accountRepository.save(account);
    }

    public boolean isDuplicated(String email) {
        return accountRepository.findByEmail(email).isPresent();
    }

    public SessionAccountDto login(LoginDto loginDto) {
        Account account = accountRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> {
            throw new IllegalArgumentException("가입되지 않은 이메일입니다");
        });

        if(!account.getPassword().equals(loginDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        return new SessionAccountDto(account);
    }
}
