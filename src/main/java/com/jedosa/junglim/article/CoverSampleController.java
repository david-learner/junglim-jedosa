package com.jedosa.junglim.article;

import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.article.domain.Article;
import com.jedosa.junglim.article.domain.Board;
import com.jedosa.junglim.article.dto.ArticleDto;
import com.jedosa.junglim.article.dto.ArticlesDto;
import com.jedosa.junglim.article.dto.CommentDto;
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
import java.util.List;

/**
 * 표지샘플 게시판
 */
@Controller
public class CoverSampleController {

    private static final Logger log = LoggerFactory.getLogger(CoverSampleController.class);
    private final ArticleService articleService;
    private final CommentService commentService;

    public CoverSampleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    /**
     * 표지샘플 글 목록
     */
    @GetMapping("board/cover-sample")
    public String coverSampleList(
            @RequestParam(required = false, defaultValue = "0", name = "page") Integer currentPage, Model model) {

        ArticleSearchCondition condition = new ArticleSearchCondition(Board.COVER_SAMPLE.getId(), Boolean.FALSE, Boolean.FALSE, currentPage, 0);
        ArticlesDto articlesDto = articleService.getArticlesOfBlockWithPagination(condition);
        model.addAttribute("articles", articlesDto.getArticleDtos());
        model.addAttribute("pagination", articlesDto.getPagination());

        return "board/cover-sample/cover-sample-list";
    }

    /**
     * 표지샘플 글 등록 폼
     */
    @GetMapping("board/cover-sample/articles")
    public String coverSampleForm(Model model, @Admin SessionAccountDto sessionAccountDto) {
        model.addAttribute("boardId", Board.COVER_SAMPLE.getId());
        return "board/cover-sample/cover-sample-form";
    }

    /**
     * 표지샘플 글 등록
     */
    @PostMapping("board/cover-sample/articles")
    public ResponseEntity<Void> writeCoverSample(@ModelAttribute ArticleDto articleDto, @Login SessionAccountDto sessionAccountDto) throws URISyntaxException {
        Article article = articleService.writeArticle(articleDto, sessionAccountDto);
        String articleLocation = "/board/cover-sample/articles/" + article.getId();
        return ResponseEntity.created(new URI(articleLocation)).build();
    }

    /**
     * 표지샘플 글 보기, 수정/삭제 폼
     */
    @GetMapping("board/cover-sample/articles/{id}")
    public String coverSampleArticle(@PathVariable Long id, Model model, @PossibleLogin SessionAccountDto sessionAccountDto) {
        List<CommentDto> comments = commentService.getCommentsOfArticle(id);
        model.addAttribute("article", articleService.getArticle(id, sessionAccountDto));
        model.addAttribute("comments", comments);
        return "board/cover-sample/cover-sample-view";
    }

    /**
     * 표지샘플 글 수정
     */
    @PatchMapping("board/cover-sample/articles/{id}")
    public ResponseEntity<Void> updateOrderQuestionArticle(@PathVariable Long id, @ModelAttribute ArticleDto articleDto, Model model, @Admin SessionAccountDto sessionAccountDto) {
        model.addAttribute("article", articleService.updateArticle(id, articleDto, sessionAccountDto));
        return ResponseEntity.ok().build();
    }

    /**
     * 표지샘플 글 삭제
     */
    @DeleteMapping("board/cover-sample/articles/{id}")
    public ResponseEntity<Void> deleteCoverSampleArticle(@PathVariable Long id, @Login SessionAccountDto sessionAccountDto) {
        articleService.deleteArticle(id, sessionAccountDto);
        return ResponseEntity.ok().build();
    }
}
