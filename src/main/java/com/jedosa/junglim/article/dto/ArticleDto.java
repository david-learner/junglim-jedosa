package com.jedosa.junglim.article.dto;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.article.domain.Article;
import com.jedosa.junglim.article.domain.ReplyStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDto {

    private Long id; // 게시글 고유 번호
    private Long memberId;
    private Long boardId; // 게시판 id
    private Long boardArticleId; // 게시글 목록 중 글의 순서를 나타내는 번호
    private String title;
    private String content;
    private ReplyStatus replyStatus;
    private LocalDateTime createdDateTime;
    private Account account;
    private Boolean isNotice;
    private String thumbnail;
    private String password;
    private Boolean isLock;
    private Long viewCount;
    private Boolean isOwn;

    public ArticleDto(Article article, Long sessionAccountId) {
        this.id = article.getId();
        this.memberId = article.getAccount().getId();
        this.boardId = article.getBoardId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.replyStatus = article.getReplyStatus();
        this.createdDateTime = article.getCreatedDateTime();
        this.account = article.getAccount();
        this.isNotice = article.getIsNotice();
        this.thumbnail = article.getThumbnail();
        this.password = article.getPassword();
        this.isLock = article.getIsLock();
        this.viewCount = article.getViewCount();
        this.isOwn = article.getAccount().getId().equals(sessionAccountId);
    }

    public ArticleDto(Article article, Account account) {
        this.id = article.getId();
        this.memberId = article.getAccount().getId();
        this.boardId = article.getBoardId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.replyStatus = article.getReplyStatus();
        this.createdDateTime = article.getCreatedDateTime();
        this.account = article.getAccount();
        this.isNotice = article.getIsNotice();
        this.thumbnail = article.getThumbnail();
        this.password = article.getPassword();
        this.isLock = article.getIsLock();
        this.viewCount = article.getViewCount();
        this.isOwn = article.isOwn(account);
    }

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.memberId = article.getAccount().getId();
        this.boardId = article.getBoardId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.replyStatus = article.getReplyStatus();
        this.createdDateTime = article.getCreatedDateTime();
        this.account = article.getAccount();
        this.isNotice = article.getIsNotice();
        this.thumbnail = article.getThumbnail();
        this.password = article.getPassword();
        this.isLock = article.getIsLock();
        this.viewCount = article.getViewCount();
        this.isOwn = article.isOwn(null);
    }

    public Article toArticle(Account account, Long boardId) {

        if (id != null) {
            return new Article(id, LocalDateTime.now(), Boolean.FALSE, boardId, account,
                    isNotice, title, null, content, thumbnail, ReplyStatus.YET, null,
                    Boolean.FALSE, 0L);
        }

        return new Article(LocalDateTime.now(), Boolean.FALSE, boardId, account,
                isNotice, title, content, thumbnail, ReplyStatus.YET, null,
                Boolean.FALSE, 0L);
    }

    public String getCreatedDate() {
        if (this.createdDateTime == null) {
            throw new IllegalStateException("No created date");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.createdDateTime.format(formatter);
    }
}
