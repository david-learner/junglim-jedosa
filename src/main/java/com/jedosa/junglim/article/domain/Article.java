package com.jedosa.junglim.article.domain;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.article.dto.ArticleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private LocalDateTime createdDateTime;
    @Column(columnDefinition = "boolean default false")
    private Boolean deleted;
    private Long boardId;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(columnDefinition = "boolean default false")
    private Boolean isNotice;
    private String title;
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;
    private String thumbnail;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'YET'")
    private ReplyStatus replyStatus;
    private String password;
    @Column(columnDefinition = "boolean default false")
    private Boolean isLock;
    @Column(columnDefinition = "bigint default 1")
    private Long viewCount;

    public Article(Long id, LocalDateTime createdDateTime, Boolean deleted, Long boardId, Account account, Boolean isNotice, String title, String content, String thumbnail, ReplyStatus replyStatus, String password, Boolean isLock, Long viewCount) {
        this.id = id;
        this.createdDateTime = createdDateTime;
        this.deleted = deleted;
        this.boardId = boardId;
        this.account = account;
        this.isNotice = isNotice;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.replyStatus = replyStatus;
        this.password = password;
        this.isLock = isLock;
        this.viewCount = viewCount;
    }

    public Article(LocalDateTime createdDateTime, Boolean deleted, Long boardId, Account account, Boolean isNotice, String title, String content, String thumbnail, ReplyStatus replyStatus, String password, Boolean isLock, Long viewCount) {
        this.createdDateTime = createdDateTime;
        this.deleted = deleted;
        this.boardId = boardId;
        this.account = account;
        this.isNotice = isNotice;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.replyStatus = replyStatus;
        this.password = password;
        this.isLock = isLock;
        this.viewCount = viewCount;
    }

    public static Article ofDefault(Long boardId, Account account) {
        return new Article(null, LocalDateTime.now(), false, boardId, account, false, "기본 글 제목", "<h1>기본 글 본문</h1>", null, ReplyStatus.YET, null, false, 1L);
    }

    public Article updateFromArticleDto (ArticleDto articleDto, Account account) {
        if (id == null || account == null) {
            throw new IllegalStateException("비정상적인 글입니다");
        }

        this.title = articleDto.getTitle();
        this.content = articleDto.getContent();
        this.isNotice = articleDto.getIsNotice();
        this.thumbnail = articleDto.getThumbnail();
        this.replyStatus = articleDto.getReplyStatus();
        this.password = articleDto.getPassword();

        return this;
    }

    public void plusViewCount() {
        this.viewCount = this.viewCount + 1;
    }

    public String getCreatedDate() {
        if (this.createdDateTime == null) {
            throw new IllegalStateException("No created date");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.createdDateTime.format(formatter);
    }

    public boolean isOwn(Account account) {
        if (account == null) {
            return false;
        }
        if(this.account.getId().equals(account.getId())) {
            return true;
        }
        return account.isAdmin();
    }

    public void delete() {
        this.deleted = true;
    }

    public void replied() {
        this.replyStatus = ReplyStatus.DONE;
    }
}
