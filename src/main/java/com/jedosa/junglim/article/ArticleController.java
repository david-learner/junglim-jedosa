package com.jedosa.junglim.article;

import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.article.domain.Article;
import com.jedosa.junglim.article.domain.Board;
import com.jedosa.junglim.article.dto.ArticleDto;
import com.jedosa.junglim.article.dto.ArticlesDto;
import com.jedosa.junglim.article.dto.CommentDto;
import com.jedosa.junglim.article.repository.ArticleSearchCondition;
import com.jedosa.junglim.infrastructure.annotation.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;
    private final CommentService commentService;

    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
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
    public ResponseEntity<Void> writeOrderQuestion(@ModelAttribute ArticleDto articleDto, @Login SessionAccountDto sessionAccountDto) throws URISyntaxException {
        Article article = articleService.writeArticle(articleDto, sessionAccountDto);
        String articleLocation = "/board/order-question/articles/" + article.getId();
        return ResponseEntity.created(new URI(articleLocation)).build();
    }

    /**
     * 주문문의 글 보기, 수정/삭제 폼
     */
    @GetMapping("board/order-question/articles/{id}")
    public String orderQuestionArticle(@PathVariable Long id, Model model, @Login SessionAccountDto sessionAccountDto) {
        List<CommentDto> comments = commentService.getCommentsOfArticle(id);
        model.addAttribute("article", articleService.getArticle(id, sessionAccountDto));
        model.addAttribute("comments", comments);
        return "board/order-question-view";
    }

    /**
     * 주문문의 글 수정
     */
    @PatchMapping("board/order-question/articles/{id}")
    public ResponseEntity<Void> updateOrderQuestionArticle(@PathVariable Long id, @ModelAttribute ArticleDto articleDto, Model model, @Login SessionAccountDto sessionAccountDto) {
        model.addAttribute("article", articleService.updateArticle(id, articleDto, sessionAccountDto));
        return ResponseEntity.ok().build();
    }

    /**
     * 주문문의 글 삭제
     */
    @DeleteMapping("board/order-question/articles/{id}")
    public ResponseEntity<Void> deleteOrderQuestionArticle(@PathVariable Long id, @Login SessionAccountDto sessionAccountDto) {
        articleService.deleteArticle(id, sessionAccountDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 주문문의 글 답변 등록
     */
    @PostMapping("board/order-question/articles/{id}/comments")
    public ResponseEntity<Void> writeCommentToOrderQuestionArticle(@PathVariable Long id, @ModelAttribute CommentDto commentDto, @Login SessionAccountDto sessionAccountDto) throws URISyntaxException {
        CommentDto createdCommentDto = commentService.replyToQuestion(id, sessionAccountDto.getId(), commentDto);
        String createdCommentUri = "/board/order-question/articles/" + createdCommentDto.getArticle().getId() + "/comments/" + createdCommentDto.getId();
        return ResponseEntity.created(new URI(createdCommentUri)).build();
    }

    /**
     * 주문문의 글 답변 수정
     */
    @PatchMapping("board/order-question/articles/*/comments/{commentId}")
    public ResponseEntity<Void> updateCommentOnOrderQuestionArticle(
            @PathVariable Long commentId,
            @ModelAttribute CommentDto commentDto,
            @Login SessionAccountDto sessionAccountDto) {

        commentService.updateComment(commentId, commentDto, sessionAccountDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 주문문의 글 답변 삭제
     */
    @DeleteMapping("board/order-question/articles/*/comments/{commentId}")
    public ResponseEntity<Void> deleteCommentOnOrderQuestionArticle(
            @PathVariable Long commentId,
            @ModelAttribute CommentDto commentDto,
            @Login SessionAccountDto sessionAccountDto) {

        commentService.deleteComment(commentId, commentDto, sessionAccountDto);
        return ResponseEntity.ok().build();
    }
}
