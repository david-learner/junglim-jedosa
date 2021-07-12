package com.jedosa.junglim.item.domain.option;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class FlyleafContentPrice extends BaseItemOption {

    public FlyleafContentPrice(Long itemId, Long id, String name, BigDecimal price) {
        super(itemId, id, name, price);
    }
}
