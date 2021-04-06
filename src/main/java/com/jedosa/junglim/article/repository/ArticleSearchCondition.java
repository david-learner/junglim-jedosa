package com.jedosa.junglim.article.repository;

import com.jedosa.junglim.article.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleSearchCondition {

    public static Integer NOTICE_LIMIT = 3;
    private Long boardId;
    private Boolean isDeleted = Boolean.FALSE;
    private Boolean isNotice = Boolean.FALSE;
    private Integer page;
    private Integer limit;

    public ArticleSearchCondition(Long boardId, Boolean isDeleted, Boolean isNotice, Integer page, Integer limit) {
        this.boardId = boardId;
        this.isDeleted = isDeleted;
        this.page = page;
        this.isNotice = isNotice;
        this.limit = limit;
    }

    public static ArticleSearchCondition ofNotice(Long boardId) {
        return new ArticleSearchCondition(boardId, Boolean.FALSE, Boolean.TRUE, 0, NOTICE_LIMIT);
    }

    public boolean isCoverSample() {
        return Board.COVER_SAMPLE.getId().equals(this.boardId);
    }
}
