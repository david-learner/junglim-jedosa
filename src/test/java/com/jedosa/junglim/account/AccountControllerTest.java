package com.jedosa.junglim.account;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.account.domain.Address;
import com.jedosa.junglim.account.domain.Grade;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.support.DummyAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("회원가입 화면")
    void signUpForm() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/sign-up"));
    }

    @Test
    @DisplayName("회원 가입하기")
        /* 비어있는 테스트 만들기 */
    void signUp() throws Exception {
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "xodnjs2549@naver.com")
                .param("password", "password1")
                .param("passwordConfirmation", "password1")
                .param("name", "황태원")
                .param("phone", "01025499229")
                .param("zipcode", "13494")
                .param("address", "경기 성남시 분당구 삼평동 681")
                .param("detailedAddress", "2층")
        ).andExpect(status().isOk());

        Account account = accountRepository.findByEmail("xodnjs2549@naver.com").orElseThrow();
        assertEquals("xodnjs2549@naver.com", account.getEmail());
        assertEquals("password1", account.getPassword());
        assertEquals("황태원", account.getName());
        assertEquals("01025499229", account.getPhone());
        assertEquals("13494", account.getAddress().getZipcode());
        assertEquals("경기 성남시 분당구 삼평동 681", account.getAddress().getAddress());
        assertEquals("2층", account.getAddress().getDetailedAddress());
    }

    @Test
    @DisplayName("이메일 중복 확인")
    void checkEmailDuplication() throws Exception {
        String duplicatedEmail = "xodnjs2549@naver.com";
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", duplicatedEmail)
                .param("password", "password1")
                .param("passwordConfirmation", "password1")
                .param("name", "황태원")
                .param("phone", "01025499229")
                .param("zipcode", "13494")
                .param("address", "경기 성남시 분당구 삼평동 681")
                .param("detailedAddress", "2층")
        ).andExpect(status().isOk());

        MockHttpServletResponse response = mockMvc.perform(get("/account/email/duplicate")
                .param("email", duplicatedEmail)
        ).andExpect(status().isBadRequest()).andReturn().getResponse();

        assertEquals("이미 가입된 이메일입니다", response.getErrorMessage());
    }

    @Test
    @DisplayName("회원정보 수정 화면")
    void accountEditForm() throws Exception {
        mockMvc.perform(get("/account/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/account-edit"));
    }

    @Test
    @DisplayName("로그인 화면")
    void loginForm() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/login"));
    }

    @Test
    @DisplayName("일반회원 로그인")
    void loginNormalUser() throws Exception {
        // given
        Account account = new Account(
                "normalTest@gmail.com", "111111", "평회원",
                "01012345678", new Address("12345", "서울시 강남구 역삼동 12", "2층"),
                true, true, Grade.NORMAL);
        SessionAccountDto sessionAccount = new SessionAccountDto(account);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("loginUser", sessionAccount);

        // when
        MockHttpServletResponse response =
                mockMvc.perform(get("/index").session(session))
                        .andExpect(status().isOk())
                        .andReturn().getResponse();

        // then
        assertTrue(response.getContentAsString().contains("로그아웃"));
    }

    @Test
    @DisplayName("로그아웃 하기")
    void logout() throws Exception {
        // given
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", DummyAccount.normalFirst.getEmail())
                .param("password", DummyAccount.normalFirst.getPassword())
                .param("passwordConfirmation", DummyAccount.normalFirst.getPassword())
                .param("name", DummyAccount.normalFirst.getName())
                .param("phone", DummyAccount.normalFirst.getPhone())
                .param("zipcode", DummyAccount.normalFirst.getAddress().getZipcode())
                .param("address", DummyAccount.normalFirst.getAddress().getAddress())
                .param("detailedAddress", DummyAccount.normalFirst.getAddress().getDetailedAddress())
        ).andExpect(status().isOk());

        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/login")
                .param("email", DummyAccount.normalFirst.getEmail())
                .param("password", DummyAccount.normalFirst.getPassword())
                .session(session)
        ).andExpect(status().isOk());

        Optional<SessionAccountDto> sessionAccountDto =
                Optional.of((SessionAccountDto) session.getAttribute("loginUser"));
        assertTrue(sessionAccountDto.stream()
                .anyMatch(account -> account.getEmail().equals(DummyAccount.normalFirst.getEmail())));

        // when
        mockMvc.perform(get("/log-out").session(session));
        sessionAccountDto = Optional.ofNullable((SessionAccountDto) session.getAttribute("loginUser"));

        // then
        assertFalse(sessionAccountDto.isPresent());
    }
}