package com.jedosa.junglim.order.dto;

import com.jedosa.junglim.item.domain.option.ItemOption;
import com.jedosa.junglim.order.domain.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Long ordererId;
    private Long itemId;
    private String itemName;
    private BigDecimal itemPrice;
    private BigDecimal vat;
    private BigDecimal totalPrice;
    private LocalDateTime generatedDateTime;

    // ItemOption
    private String paperSize; // 출력물 사이즈
    private String bindingType; // 제본 형식 (무선, 중철, 링)
    private String bindingDirection; // 제본 방향 (좌철, 상철)
    private String coverPrintingType; // 커버 출력 형식 (양면, 단면)
    private String coverPrintingColorType; // 커버 출력 색상 (흑백, 컬러)
    private String coverPaperType; // 커버 종이 타입
    private String coverCoatingType; // 커버 코팅 형식
    private String coverDesignName; // 커버 디자인 이름
    private String contentPrintingType; // 본문 출력 형식 (양면, 단면)
    private String contentPageCount; // 본문 페이지 수
    private String contentPaperType; // 본문 종이 타입
    private Boolean hasFlyleaf; // 간지 추가 여부
    private String flyleafColorType; // 간지 색상
    private Integer flyleafCountPerBook; // 1부당 간지 수량
    private Boolean hasFlyleafContentPrinting; // 간지 문구 인쇄 여부
    private String flyleafContentPrintingValue; // 간지 인쇄 문구
    private String flyleafInsertLocation; // 간지 삽입 위치
    private Integer pageCountPerBook; // 1부당 페이지 수
    private Integer bookCount; // 부수

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.ordererId = orderItem.getOrdererId();
        this.itemId = orderItem.getItemId();
        this.itemName = orderItem.getItemName();
        this.itemPrice = orderItem.getItemPrice();
        this.vat = orderItem.getVat();
        this.totalPrice = orderItem.getTotalPrice();
        this.generatedDateTime = orderItem.getGeneratedDateTime();

        this.paperSize = orderItem.getItemOption().getPaperSize();
        this.bindingType = orderItem.getItemOption().getBindingType();
        this.bindingDirection = orderItem.getItemOption().getBindingDirection();
        this.coverPrintingType = orderItem.getItemOption().getCoverPrintingType();
        this.coverPrintingColorType = orderItem.getItemOption().getCoverPrintingColorType();
        this.coverPaperType = orderItem.getItemOption().getCoverPaperType();
        this.coverCoatingType = orderItem.getItemOption().getCoverCoatingType();
        this.coverDesignName = orderItem.getItemOption().getCoverDesignName();
        this.contentPrintingType = orderItem.getItemOption().getContentPrintingType();
        this.contentPageCount = orderItem.getItemOption().getContentPageCount();
        this.contentPaperType = orderItem.getItemOption().getContentPaperType();
        this.hasFlyleaf = orderItem.getItemOption().getHasFlyleaf();
        this.flyleafColorType = orderItem.getItemOption().getFlyleafColorType();
        this.flyleafCountPerBook = orderItem.getItemOption().getFlyleafCountPerBook();
        this.hasFlyleafContentPrinting = orderItem.getItemOption().getHasFlyleafContentPrinting();
        this.flyleafContentPrintingValue = orderItem.getItemOption().getFlyleafContentPrintingValue();
        this.flyleafInsertLocation = orderItem.getItemOption().getFlyleafInsertLocation();
        this.pageCountPerBook = orderItem.getItemOption().getPageCountPerBook();
        this.bookCount = orderItem.getItemOption().getBookCount();
    }

    public OrderItem toOrderItem() {
        ItemOption itemOption = ItemOption.builder()
                .paperSize(paperSize)
                .bindingType(bindingType)
                .bindingDirection(bindingDirection)
                .coverPrintingType(coverPrintingType)
                .coverPrintingColorType(coverPrintingColorType)
                .coverPaperType(coverPaperType)
                .coverCoatingType(coverCoatingType)
                .coverDesignName(coverDesignName)
                .contentPrintingType(contentPrintingType)
                .contentPageCount(contentPageCount)
                .contentPaperType(contentPaperType)
                .hasFlyleaf(hasFlyleaf)
                .flyleafColorType(flyleafColorType)
                .flyleafCountPerBook(flyleafCountPerBook)
                .hasFlyleafContentPrinting(hasFlyleafContentPrinting)
                .flyleafContentPrintingValue(flyleafContentPrintingValue)
                .flyleafInsertLocation(flyleafInsertLocation)
                .pageCountPerBook(pageCountPerBook)
                .bookCount(bookCount).build();

        return OrderItem.builder()
                .ordererId(ordererId)
                .itemId(itemId)
                .itemName(itemName)
                .itemPrice(itemPrice)
                .vat(vat)
                .totalPrice(totalPrice)
                // ZoneId에 KST가 없어서, UTC+09:00으로 섫정하여 KST와 동일하게 설정
                .generatedDateTime(LocalDateTime.now(Clock.offset(Clock.systemUTC(), Duration.ofHours(9))))
                .isDeleted(Boolean.FALSE)
                .isInCart(Boolean.TRUE)
                .itemOption(itemOption)
                .build();
    }

    public String getItemOptionsAsHtml() {
        if (itemId == 1L) {
            List<String> itemOptions = new ArrayList<>();
            itemOptions.add("사이즈: " + paperSize);
            itemOptions.add("제본형식: " + bindingType);
            itemOptions.add("제본방향: " + bindingDirection);
            // 표지
            itemOptions.add("표지 출력 형식: " + coverPrintingType);
            itemOptions.add("표지 출력 색상: " + coverPrintingColorType);
            itemOptions.add("표지 종이: " + coverPaperType);
            itemOptions.add("표지 코팅: " + coverCoatingType);
            itemOptions.add("표지 디자인: " + coverDesignName);
            // 본문
            itemOptions.add("본문 출력 형식: " + contentPrintingType);
            itemOptions.add("본문 종이: " + contentPaperType);
            itemOptions.add("본문 페이지 수: " + contentPageCount);
            // 간지 여부
            if (hasFlyleaf) {
                itemOptions.add("<b>간지 추가</b>");
                itemOptions.add("간지 색상: " + flyleafColorType);
                itemOptions.add("1부당 간지 수량: " + flyleafCountPerBook);
            }
            if (hasFlyleafContentPrinting) {
                itemOptions.add("<b>간지 문구 인쇄</b>");
                itemOptions.add("간지 인쇄 문구: " + flyleafContentPrintingValue);
                itemOptions.add("간지 삽입 위치: " + flyleafInsertLocation);
            }
            itemOptions.add("1부당 페이지 수: " + pageCountPerBook);

            return String.join("<br>", itemOptions);
        }
        return null;
    }
}
