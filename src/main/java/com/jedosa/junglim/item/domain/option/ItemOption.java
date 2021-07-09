package com.jedosa.junglim.item.domain.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ItemOption {

    private String paperSize; // 출력물 사이즈
    private String bindingType; // 제본 유형 (무선, 중철, 링)
    private String bindingDirection; // 제본 방향 (좌철, 상철)
    private String coverPrintingType; // 커버 출력 형식 (양면, 단면)
    private String coverPrintingColorType; // 커버 출력 색상 (흑백, 컬러)
    private String coverPaperType; // 커버 종이 타입
    private String coverCoatingType; // 커버 코팅 형식
    private String coverDesignName; // 커버 디자인 이름
    private String contentPrintingType; // 본문 출력 형식 (양면, 단면)
    private String contentPrintingColorType; // 본문 출력 색상 (흑백, 컬러)
    private String contentPageCount; // 본문 페이지 수
    private String contentPaperType; // 본문 종이 타입
    private Boolean hasFlyleaf; // 간지 추가 여부
    private String flyleafColorType; // 간지 색상
    private Integer flyleafCountPerBook; // 1부당 간지 수량
    private Boolean hasFlyleafContentPrinting; // 간지 문구 인쇄 여부
    private String flyleafContentPrintingValue; // 간지 인쇄 문구
    private String flyleafInsertLocation; // 간지 삽입 위치
    private String customerComment; // 고객 요청사항
    private Integer pageCountPerBook; // 1부당 페이지 수
    private Integer bookCount; // 부수
}
