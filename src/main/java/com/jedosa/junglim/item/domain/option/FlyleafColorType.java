package com.jedosa.junglim.item.domain.option;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class FlyleafColorType extends BaseItemOption {

    public FlyleafColorType(Long itemId, Long id, String name, BigDecimal price) {
        super(itemId, id, name, price);
    }
}
