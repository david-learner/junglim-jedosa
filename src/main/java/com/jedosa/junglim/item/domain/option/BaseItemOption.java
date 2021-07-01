package com.jedosa.junglim.item.domain.option;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class BaseItemOption {

    Long itemId;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    BigDecimal price;

    public BaseItemOption(Long itemId, Long id, String name, BigDecimal price) {
        this.itemId = itemId;
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
