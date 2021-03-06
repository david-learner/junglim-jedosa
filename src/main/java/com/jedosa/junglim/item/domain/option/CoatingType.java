package com.jedosa.junglim.item.domain.option;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class CoatingType extends BaseItemOption {

    public CoatingType(Long itemId, Long id, String name, BigDecimal price) {
        super(itemId, id, name, price);
    }
}
