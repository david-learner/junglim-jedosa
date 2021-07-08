package com.jedosa.junglim.item.domain.option;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
// 종이 유형, 규격, 출력, 금액을 묶어놓은 엔티티
public class PaperPrintingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private String category;
    private String name;
    private String size;
    private String printingColor;
    private BigDecimal singleSidePrice;
    private BigDecimal doubleSidePrice;

    public PaperPrintingType(Long id,
                             Long itemId,
                             String category,
                             String name,
                             String size,
                             String printingColor,
                             BigDecimal singleSidePrice,
                             BigDecimal doubleSidePrice) {
        this.id = id;
        this.itemId = itemId;
        this.category = category;
        this.name = name;
        this.size = size;
        this.printingColor = printingColor;
        this.singleSidePrice = singleSidePrice;
        this.doubleSidePrice = doubleSidePrice;
    }
}
