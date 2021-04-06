package com.jedosa.junglim.admin;

import com.jedosa.junglim.account.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void adminIndex() throws Exception {
        // given
        Account account = new Account(
                "admin@gmail.com", "111111", "관리자",
                "01012345678", new Address("12345", "서울시 강남구 역삼동 12", "2층"),
                true, true, Grade.NORMAL);
        account.upgradeToAdmin();

        SessionAccountDto sessionAccount = new SessionAccountDto(account);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionAccount.KEY, sessionAccount);

        // when
        mockMvc.perform(get("/admin").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/admin-index"));

        // then
    }
}
