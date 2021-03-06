package com.jedosa.junglim.board;

import com.jedosa.junglim.account.AccountRepository;
import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.account.domain.SessionAccount;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.article.domain.Article;
import com.jedosa.junglim.article.domain.Board;
import com.jedosa.junglim.article.repository.ArticleRepository;
import com.jedosa.junglim.support.DummyAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.Objects;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ArticleRepository articleRepository;

    private MockHttpSession session = new MockHttpSession();
    private MockHttpSession loggedInSession = new MockHttpSession();

    @BeforeEach
    public void setup() {
        loggedInSession.setAttribute(SessionAccount.KEY, DummyAccount.sessionNormalFirst);
    }

    @Test
    @DisplayName("????????? ??? ???????????? ??? ?????? ??????")
    public void orderQuestionList() throws Exception {
        // given
        // when
        mockMvc.perform(get("/board/order-question"))
                //then
                .andExpect(status().isOk())
                .andExpect(view().name("board/order-question/order-question-list"));
    }

    @Test
    @DisplayName("????????? ??? ???????????? ??? ?????? ??????")
    public void orderQuestionFormBeforeLogIn() throws Exception {
        // given
        // when
        mockMvc.perform(get("/board/order-question/form"))
                // then
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("???????????? ????????? ??????????????????")));
    }

    @Test
    @DisplayName("????????? ??? ???????????? ??? ?????? ??????")
    public void orderQuestionFormAfterLogIn() throws Exception {
        // given
        // when
        mockMvc.perform(get("/board/order-question/form").session(loggedInSession))
                // then
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("????????? ??? ???????????? ??? ??????")
    public void writeOrderQuestionBeforeLogIn() throws Exception {
        // given
        Account loginUser = accountRepository.save(DummyAccount.normalFirst);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("memberId", String.valueOf(loginUser.getId()));
        formData.add("boardId", String.valueOf(Board.ORDER_QUESTION.getId()));
        formData.add("title", "????????? ??? ???????????? ??? ?????? ????????? ??????");
        formData.add("content", "????????? ??? ???????????? ??? ?????? ????????? ??????");

        // when
        mockMvc.perform(post("/board/order-question")
                .params(formData)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                // then
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("?????????")));
    }

    @Test
    @DisplayName("????????? ??? ???????????? ??? ??????")
    public void writeOrderQuestionAfterLogIn() throws Exception {
        // given
        Account loginUser = accountRepository.save(DummyAccount.normalFirst);
        MockHttpSession newSession = new MockHttpSession();
        newSession.setAttribute(SessionAccount.KEY, new SessionAccountDto(loginUser));
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("memberId", String.valueOf(loginUser.getId()));
        formData.add("boardId", String.valueOf(Board.ORDER_QUESTION.getId()));
        formData.add("title", "????????? ??? ???????????? ??? ?????? ????????? ??????");
        formData.add("content", "????????? ??? ???????????? ??? ?????? ????????? ??????");

        // when
        mockMvc.perform(post("/board/order-question")
                .session(newSession)
                .params(formData)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                // then
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("????????? ??? ???????????? ??? ??????")
    public void orderQuestionArticleDetailViewWithoutLogin() throws Exception {
        // given
        // when
        mockMvc.perform(get("/board/order-question/1"))
                // then
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("?????????")));
    }

    @Test
    @DisplayName("??? ???????????? ????????? ??? ???????????? ??? ??????")
    public void orderQuestionArticleDetailViewAfterOwnerLogIn() throws Exception {
        // given
        Account loginUser = accountRepository.save(DummyAccount.normalFirst);
        loggedInSession.setAttribute(SessionAccount.KEY, new SessionAccountDto(loginUser));
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("memberId", String.valueOf(loginUser.getId()));
        formData.add("boardId", String.valueOf(Board.ORDER_QUESTION.getId()));
        formData.add("title", "??? ???????????? ????????? ??? ???????????? ??? ?????? ????????? ??????");
        formData.add("content", "??? ???????????? ????????? ??? ???????????? ??? ?????? ????????? ??????");
        MockHttpServletResponse response = mockMvc.perform(post("/board/order-question")
                .session(loggedInSession)
                .params(formData)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        URI articleLocation = new URI(Objects.requireNonNull(response.getHeader(HttpHeaders.LOCATION)));
        // when
        mockMvc.perform(get(articleLocation).session(loggedInSession))
        // then
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("?????? ??? ???????????? ?????? ???????????? ????????? ??? ???????????? ??? ??????")
    public void orderQuestionArticleDetailViewAfterNotOwnerLogIn() throws Exception {
        // given
        Account owner = accountRepository.save(DummyAccount.normalFirst);
        loggedInSession.setAttribute(SessionAccount.KEY, new SessionAccountDto(owner));
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("memberId", String.valueOf(owner.getId()));
        formData.add("boardId", String.valueOf(Board.ORDER_QUESTION.getId()));
        formData.add("title", "?????? ??? ???????????? ?????? ???????????? ????????? ??? ???????????? ??? ?????? ????????? ??????");
        formData.add("content", "?????? ??? ???????????? ?????? ???????????? ????????? ??? ???????????? ??? ?????? ????????? ??????");
        MockHttpServletResponse response = mockMvc.perform(post("/board/order-question")
                .session(loggedInSession)
                .params(formData)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        URI articleLocation = new URI(Objects.requireNonNull(response.getHeader(HttpHeaders.LOCATION)));

        Account notOwner = accountRepository.save(DummyAccount.normalSecond);
        loggedInSession.setAttribute(SessionAccount.KEY, new SessionAccountDto(notOwner));
        // when
        mockMvc.perform(get(articleLocation).session(loggedInSession))
        // then
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("??????")));
    }

    @Test
    @DisplayName("????????? ??? ???????????? ??? ????????????")
    public void orderQuestionArticleUpdateBeforeLogIn() throws Exception {
        // given
        Account owner = accountRepository.save(DummyAccount.normalFirst);
        Long orderQuestionBoardId = Board.ORDER_QUESTION.getId();
        Article article = Article.ofDefault(orderQuestionBoardId, owner);
        Article createdArticle = articleRepository.save(article);
        Long articleId = createdArticle.getId();
        // when
        mockMvc.perform(patch("/board/order-question/" + articleId))
        // then
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("?????????")));
    }

    @Test
    @DisplayName("????????? ??? ????????? ???????????? ?????? ???????????? ??? ????????????")
    public void orderQuestionArticleUpdateAfterLogInAsNotOwner() throws Exception {
        // given
        Account owner = accountRepository.save(DummyAccount.normalFirst);
        Account notOwner = accountRepository.save(DummyAccount.normalSecond);
        Long orderQuestionBoardId = Board.ORDER_QUESTION.getId();
        Article article = Article.ofDefault(orderQuestionBoardId, owner);
        Article createdArticle = articleRepository.save(article);
        Long articleId = createdArticle.getId();
        // ??? ????????? ?????? ????????? ?????????
        session.setAttribute(SessionAccount.KEY, new SessionAccountDto(notOwner));

        // when
        mockMvc.perform(patch("/board/order-question/" + articleId).session(session))
                // then
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("?????? ??????")));
    }

    @Test
    @DisplayName("????????? ??? ????????? ???????????? ??? ????????????")
    public void orderQuestionArticleUpdateAfterLogInAsOwner() throws Exception {
        // given
        Account owner = accountRepository.save(DummyAccount.normalFirst);
        Long orderQuestionBoardId = Board.ORDER_QUESTION.getId();
        Article article = Article.ofDefault(orderQuestionBoardId, owner);
        Article createdArticle = articleRepository.save(article);
        Long articleId = createdArticle.getId();
        // ??? ????????? ?????? ????????? ?????????
        session.setAttribute(SessionAccount.KEY, new SessionAccountDto(owner));

        // when
        mockMvc.perform(patch("/board/order-question/" + articleId).session(session))
                // then
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("????????? ??? ???????????? ??? ??????")
    public void deleteOrderQuestionBeforeLogIn() throws Exception {
        // given
        Account loginUser = accountRepository.save(DummyAccount.normalFirst);
        loggedInSession.setAttribute(SessionAccount.KEY, new SessionAccountDto(loginUser));
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("memberId", String.valueOf(loginUser.getId()));
        formData.add("boardId", String.valueOf(Board.ORDER_QUESTION.getId()));
        formData.add("title", "???????????? ??? ?????? ????????? ??????");
        formData.add("content", "???????????? ??? ?????? ????????? ??????");
        MockHttpServletResponse response = mockMvc.perform(post("/board/order-question")
                .session(loggedInSession)
                .params(formData)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        String articleLocation = response.getHeader(HttpHeaders.LOCATION);
        assert articleLocation != null;
        // when
        mockMvc.perform(delete(articleLocation)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        // then
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("?????????")));
    }

    @Test
    @DisplayName("????????? ??? ????????? ???????????? ?????? ???????????? ??? ??????")
    public void deleteOrderQuestionAfterLogInAsNotOwner() throws Exception {
        // given
        Account loginUser = accountRepository.save(DummyAccount.normalFirst);
        loggedInSession.setAttribute(SessionAccount.KEY, new SessionAccountDto(loginUser));
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("memberId", String.valueOf(loginUser.getId()));
        formData.add("boardId", String.valueOf(Board.ORDER_QUESTION.getId()));
        formData.add("title", "???????????? ??? ?????? ????????? ??????");
        formData.add("content", "???????????? ??? ?????? ????????? ??????");
        MockHttpServletResponse response = mockMvc.perform(post("/board/order-question")
                .session(loggedInSession)
                .params(formData)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        String articleLocation = response.getHeader(HttpHeaders.LOCATION);
//        Assertions.assertThat(articleLocation).isNotNull();
        assert articleLocation != null;
        Account notOwner = accountRepository.save(DummyAccount.normalSecond);
        session.setAttribute(SessionAccount.KEY, new SessionAccountDto(notOwner));
        // when
        mockMvc.perform(delete(articleLocation)
                .session(session)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        // then
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("??????")));
    }

    @Test
    @DisplayName("????????? ??? ????????? ????????? ???????????? ??? ??????")
    public void deleteOrderQuestionAfterLogInAsOwner() throws Exception {
        // given
        Account loginUser = accountRepository.save(DummyAccount.normalFirst);
        loggedInSession.setAttribute(SessionAccount.KEY, new SessionAccountDto(loginUser));
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("memberId", String.valueOf(loginUser.getId()));
        formData.add("boardId", String.valueOf(Board.ORDER_QUESTION.getId()));
        formData.add("title", "???????????? ??? ?????? ????????? ??????");
        formData.add("content", "???????????? ??? ?????? ????????? ??????");
        MockHttpServletResponse response = mockMvc.perform(post("/board/order-question")
                .session(loggedInSession)
                .params(formData)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        String articleLocation = response.getHeader(HttpHeaders.LOCATION);
//        Assertions.assertThat(articleLocation).isNotNull();
        assert articleLocation != null;
        Account notOwner = accountRepository.save(DummyAccount.normalSecond);
        session.setAttribute(SessionAccount.KEY, new SessionAccountDto(notOwner));
        // when
        mockMvc.perform(delete(articleLocation)
                .session(session)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                // then
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("??????")));
    }
}