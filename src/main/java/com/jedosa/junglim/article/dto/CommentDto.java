package com.jedosa.junglim.article.dto;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.article.domain.Article;
import com.jedosa.junglim.article.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private Article article;
    private Account account;
    private LocalDateTime createdDateTime;
    private String content;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.article = comment.getArticle();
        this.account = comment.getAccount();
        this.createdDateTime = comment.getCreatedDateTime();
        this.content = comment.getContent();
    }

    public String getCreatedDate() {
        if (this.createdDateTime == null) {
            throw new IllegalStateException("No created date");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.createdDateTime.format(formatter);
    }

    public Comment toComment() {
        return new Comment(article, account, content);
    }
}
