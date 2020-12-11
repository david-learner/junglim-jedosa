package com.jedosa.junglim.article.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleSearchCondition {

    private Long boardId;
    private Boolean isDeleted;
}
