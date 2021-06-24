package com.jedosa.junglim.item.dto;

import com.jedosa.junglim.item.domain.option.BindingType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
public class BindingTypeDto {

    private Long itemId; // 제본유형이 적용될 아이템의 id ex) 인쇄 및 제본(1L), 현수막(2L) ..
    private Long id; // 제본유형 id
    private String name; // 제본유형
    private BigDecimal price;

    public BindingType toBindingType() {
        return new BindingType(id, itemId, name, price);
    }
}
