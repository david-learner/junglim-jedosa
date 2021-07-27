package com.jedosa.junglim.article;

import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.article.ArticleController;
import com.jedosa.junglim.article.ArticleService;
import com.jedosa.junglim.article.domain.Article;
import com.jedosa.junglim.article.domain.Board;
import com.jedosa.junglim.article.dto.ArticleDto;
import com.jedosa.junglim.article.dto.ArticlesDto;
import com.jedosa.junglim.article.repository.ArticleSearchCondition;
import com.jedosa.junglim.infrastructure.annotation.Admin;
import com.jedosa.junglim.infrastructure.annotation.Login;
import com.jedosa.junglim.infrastructure.annotation.PossibleLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class NoticeController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;

    public NoticeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 공지사항 게시글 목록
     */
    @GetMapping("board/notice")
    public String noticeList(
            @RequestParam(required = false, defaultValue = "0") Integer page, Model model) {

        ArticleSearchCondition condition = new ArticleSearchCondition();
        condition.setBoardId(Board.NOTICE.getId());
        condition.setPage(page);

        ArticlesDto articlesDto = articleService.getArticlesOfBlockWithPagination(condition);
        model.addAttribute("articles", articlesDto.getArticleDtos());
        model.addAttribute("pagination", articlesDto.getPagination());

        return "board/notice/notice-list";
    }

    /**
     * 공지사항 글 등록 폼
     */
    @GetMapping("board/notice/form")
    public String noticeForm(Model model) {
        model.addAttribute("boardId", Board.NOTICE.getId());
        return "board/notice/notice-form";
    }

    /**
     * 공지사항 글 등록
     */
    @PostMapping("board/notice")
    public ResponseEntity<Void> writeNotice(@ModelAttribute ArticleDto articleDto, @Admin SessionAccountDto sessionAccountDto) throws URISyntaxException {
        Article article = articleService.writeArticle(articleDto, sessionAccountDto);
        String articleLocation = "/board/notice/" + article.getId();
        return ResponseEntity.created(new URI(articleLocation)).build();
    }

    /**
     * 공지사항 글 보기, 수정/삭제 폼
     */
    @GetMapping("board/notice/{id}")
    public String notice(@PathVariable Long id, Model model, @PossibleLogin SessionAccountDto sessionAccountDto) {
        model.addAttribute("article", articleService.getArticle(id, sessionAccountDto));
        return "board/notice/notice-view";
    }

    /**
     * 공지사항 글 수정
     */
    @PatchMapping("board/notice/{id}")
    public ResponseEntity<Void> updateNotice(
            @PathVariable Long id, @ModelAttribute ArticleDto articleDto,
            Model model, @Admin SessionAccountDto sessionAccountDto) {
        model.addAttribute("article", articleService.updateArticle(id, articleDto, sessionAccountDto));
        return ResponseEntity.ok().build();
    }

    /**
     * 공지사항 글 삭제
     */
    @DeleteMapping("board/notice/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id, @Login SessionAccountDto sessionAccountDto) {
        articleService.deleteArticle(id, sessionAccountDto);
        return ResponseEntity.ok().build();
    }
}
