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
public class BindingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId; // 제본유형이 적용될 아이템의 id ex) 인쇄 및 제본(1L), 현수막(2L) ..
    private String name; // 제본유형
    private BigDecimal price;

    public BindingType(Long id, Long itemId, String name, BigDecimal price) {
        if (id != null) {
            this.id = id;
        }
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }
}
