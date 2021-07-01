package com.jedosa.junglim.item.dto;

import com.jedosa.junglim.item.domain.option.BindingType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public abstract class BaseItemOptionDto {

    private Long itemId; // 제본유형이 적용될 아이템의 id ex) 인쇄 및 제본(1L), 현수막(2L) ..
    private Long id;
    private String name;
    private BigDecimal price;

    public BaseItemOptionDto(Long itemId, Long id, String name, BigDecimal price) {
        this.itemId = itemId;
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
