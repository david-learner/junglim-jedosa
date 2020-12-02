package com.jedosa.junglim.article.dto;

import com.jedosa.junglim.article.domain.Pagination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * ArticleService에서 ArticleController로 이동할 때 사용하는 DTO
 * 사용자에게 전달될 한 페이지 분량의 게시글들과 페이지 정보를 포함하고 있다
 */
@Getter
@Setter
public class ArticlesDto {
    List<ArticleDto> articleDtos;
    Pagination pagination;

    public ArticlesDto(List<ArticleDto> articleDtos, Pagination pagination) {
        this.articleDtos = articleDtos;
        this.pagination = pagination;
    }
}
