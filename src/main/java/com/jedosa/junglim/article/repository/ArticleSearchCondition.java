package com.jedosa.junglim.article.repository;

import lombok.Data;

@Data
public class ArticleSearchCondition {

    private Long boardId;
    private Boolean isDeleted;
}
