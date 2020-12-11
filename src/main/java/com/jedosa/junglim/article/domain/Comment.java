package com.jedosa.junglim.article.domain;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.exception.NotOwnException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(columnDefinition = "timestamp default now()")
    private LocalDateTime createdDateTime;
    @Column(columnDefinition = "boolean default false")
    private Boolean deleted;
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;

    public Comment(Article article, Account account, String content) {
        this.article = article;
        this.account = account;
        this.content = content;
        this.createdDateTime = LocalDateTime.now();
        this.deleted = Boolean.FALSE;
    }

    public void updateContent(Account account, Comment comment) {
        if(!isOwn(account)) {
            throw new NotOwnException();
        }
        this.content = comment.getContent();
    }

    private boolean isOwn(Account account) {
        return this.account.getId().equals(account.getId());
    }

    public void delete(Account account) {
        if(!isOwn(account)) {
            throw new NotOwnException();
        }
        this.deleted = true;
    }
}
