package com.jedosa.junglim.account;

import com.jedosa.junglim.account.domain.LoginDto;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.account.domain.SignUpDto;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.net.URI;

@Controller
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("sign-up")
    public String signUpForm(Model model) {
        return "account/sign-up";
    }

    @PostMapping("sign-up")
    public ResponseEntity<Void> signUp(@ModelAttribute SignUpDto signupDto) {
        accountService.signUp(signupDto.toAccount());
        return ResponseEntity.status(HttpStatus.OK).location(URI.create("/index")).build();
    }

    @GetMapping("account/email/duplicate")
    public ResponseEntity<Void> checkEmailDuplication(@RequestParam String email) {
        log.debug("email duplication: '{}'", email);
        if (accountService.isDuplicated(email)) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("account/edit")
    public String accountEditForm() {
        return "account/account-edit";
    }

    @GetMapping("login")
    public String loginForm() {
        return "account/login";
    }

    @PostMapping("login")
    public ResponseEntity<Void> login(@ModelAttribute LoginDto loginDto, HttpSession session) {
        SessionAccountDto sessionAccountDto = accountService.login(loginDto);
        session.setAttribute("loginUser", sessionAccountDto);
        return ResponseEntity.status(HttpStatus.OK).location(URI.create("/index")).build();
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "home/index";
    }
}
