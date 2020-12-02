package com.jedosa.junglim.article;

import com.jedosa.junglim.account.AccountRepository;
import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.account.domain.SessionAccount;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.article.domain.Article;
import com.jedosa.junglim.article.domain.Board;
import com.jedosa.junglim.article.dto.ArticleDto;
import com.jedosa.junglim.article.dto.ArticlesDto;
import com.jedosa.junglim.article.repository.ArticleRepository;
import com.jedosa.junglim.article.repository.ArticleSearchCondition;
import com.jedosa.junglim.exception.NoAccountException;
import com.jedosa.junglim.exception.NoArticleException;
import com.jedosa.junglim.exception.NotOwnException;
import com.jedosa.junglim.infrastructure.HttpSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final AccountRepository accountRepository;

    public ArticleController(ArticleService articleService, ArticleRepository articleRepository, AccountRepository accountRepository) {
        this.articleService = articleService;
        this.articleRepository = articleRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * 주문문의 글 목록
     */
    @GetMapping("board/order-question")
    public String orderQuestionList(
            @RequestParam(required = false, defaultValue = "0", name = "page") Integer currentPage, Model model) {

        // generate CONDITION from Handler Parameter
        // 게시판별 DefaultSearchCondition 바로 불러올 수 있게?
        ArticleSearchCondition condition = new ArticleSearchCondition();
        condition.setIsDeleted(false);
        condition.setBoardId(Board.ORDER_QUESTION.getId());

        ArticlesDto articlesDto = articleService.getArticlesOfBlockWithPagination(currentPage, condition);
        model.addAttribute("articles", articlesDto.getArticleDtos());
        model.addAttribute("pagination", articlesDto.getPagination());

        return "board/order-question-list";
    }

    /**
     * 주문문의 글 등록 폼
     */
    @GetMapping("board/order-question/articles")
    public String orderQuestionForm(Model model) {
        model.addAttribute("boardId", Board.ORDER_QUESTION.getId());
        return "board/order-question-form";
    }

    /**
     * 주문문의 글 등록
     */
    @PostMapping("board/order-question/articles")
    public ResponseEntity writeOrderQuestion(@ModelAttribute ArticleDto articleDto, HttpSession session) {
        SessionAccountDto sessionAccountDto = (SessionAccountDto) session.getAttribute(SessionAccount.KEY);
        Account account = accountRepository.findById(sessionAccountDto.getId()).orElseThrow(NoAccountException::new);
        Article article = articleDto.toArticle(account, articleDto.getBoardId());
        articleRepository.save(article);
        return ResponseEntity.ok().build();
    }

    /**
     * 주문문의 글 보기, 수정/삭제 폼
     */
    @GetMapping("board/order-question/articles/{id}")
    public String orderQuestionArticle(@PathVariable Long id, Model model, HttpSession session) {
        Article article = articleRepository.findById(id).orElseThrow(NoArticleException::new);
        SessionAccountDto sessionAccountDto = (SessionAccountDto) session.getAttribute(SessionAccount.KEY);
        ArticleDto articleDto = new ArticleDto(article, sessionAccountDto.getId());
        model.addAttribute("article", articleDto);
        return "board/order-question-view";
    }

    /**
     * 주문문의 글 수정
     */
    @PatchMapping("board/order-question/articles/{id}")
    public ResponseEntity updateOrderQuestionArticle(@PathVariable Long id, @ModelAttribute ArticleDto articleDto, HttpSession session, Model model) {
        // Account 정보 로드
        Long sessionAccountId = HttpSessionUtils.getAccountId(session);
        Account account = accountRepository.findById(sessionAccountId).orElseThrow(NoAccountException::new);

        // ArticleDto to Article
        Article article = articleRepository.findById(id).orElseThrow(NoArticleException::new);
        // isOwnArticle
        if (!article.isOwn(account)) {
            throw new NotOwnException();
        }
        article.updateFromArticleDto(articleDto, account);
        // Article
        Article savedArticle = articleRepository.save(article);
        ArticleDto updatedArticleDto = new ArticleDto(savedArticle, sessionAccountId);
        model.addAttribute("article", updatedArticleDto);

        return ResponseEntity.ok().build();
    }

    /**
     * 주문문의 글 삭제
     */
    @DeleteMapping("board/order-question/articles/{id}")
    public ResponseEntity deleteOrderQuestionArticle(@PathVariable Long id, @ModelAttribute ArticleDto articleDto, HttpSession session, Model model) {
        // Account 정보 로드
        Long sessionAccountId = HttpSessionUtils.getAccountId(session);
        Account account = accountRepository.findById(sessionAccountId).orElseThrow(NoAccountException::new);

        // ArticleDto to Article
        Article article = articleRepository.findById(id).orElseThrow(NoArticleException::new);
        // isOwnArticle
        if (!article.isOwn(account)) {
            throw new NotOwnException();
        }

        article.delete();
        // Article
        Article savedArticle = articleRepository.save(article);

        return ResponseEntity.ok().build();
    }
}
