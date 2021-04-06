package com.jedosa.junglim.article.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString(of = {"page", "totalPages", "blockStart", "blockEnd", "numbers"})
public class Pagination {

    public static final Integer BLOCK_SIZE = 10;
    public static final Integer GALLERY_BLOCK_SIZE = 12;
    private Integer page;
    private Integer totalPages;
    private Integer blockStart;
    private Integer blockEnd;
    private Long pageTopNumber;
    private List<Integer> numbers;
    public Pagination(Page<?> elementsPage) {
        Integer page = elementsPage.getNumber();
        int totalPages = elementsPage.getTotalPages();
        long totalElements = elementsPage.getTotalElements();
        if (totalElements > BLOCK_SIZE) {
            pageTopNumber = totalElements - (page * BLOCK_SIZE);
        }
        pageTopNumber = totalElements;

        if (totalPages < 0) {
            throw new IllegalArgumentException("페이지 정보가 잘못되었습니다");
        }

        this.page = page;
        this.totalPages = totalPages;
        this.blockStart = ((page / BLOCK_SIZE) * BLOCK_SIZE) + 1;
        int calculatedBlockEnd = ((page / BLOCK_SIZE) * BLOCK_SIZE) + BLOCK_SIZE;
        if (totalPages < calculatedBlockEnd) {
            this.blockEnd = totalPages;
        } else {
            this.blockEnd = calculatedBlockEnd;
        }
        this.numbers = new ArrayList<>();
        for (int index = this.blockStart; index <= this.blockEnd; index++) {
            this.numbers.add(index);
        }
    }

    /**
     * 게시글 목록 중 한 블록(페이지)의 최상단에 위치하는 숫자
     * 게시글의 고유번호는 아니며 오직 게시글의 순서만 나타낸다
     */
    public static long calculateTopBoardArticleNumber(int page, long totalElements) {
        if (totalElements > BLOCK_SIZE) {
            return totalElements - (page * BLOCK_SIZE);
        }
         return totalElements;
    }

    public Integer getPreviousPage() {
        int prevPage = page - 1;
        if (prevPage < 0) {
            prevPage = 0;
        }
        return prevPage;
    }

    public Integer getNextPage() {
        int nextPage = page + 1;
        if (nextPage > getBlockEnd()) {
            nextPage = getBlockEnd();
        }
        return nextPage;
    }

    public Integer getPageForUser() {
        return page+1;
    }
}
