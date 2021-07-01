package com.jedosa.junglim.item.domain.option;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class DesignType extends BaseItemOption {

    public DesignType(Long itemId, Long id, String name, BigDecimal price) {
        super(itemId, id, name, price);
    }
}
