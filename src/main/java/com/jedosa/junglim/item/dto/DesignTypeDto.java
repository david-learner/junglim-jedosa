package com.jedosa.junglim.item.dto;

import com.jedosa.junglim.item.domain.option.DesignType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DesignTypeDto extends BaseItemOptionDto implements Domainable {

    public DesignTypeDto(Long itemId, Long id, String name, BigDecimal price) {
        super(itemId, id, name, price);
    }

    public DesignTypeDto(DesignType type) {
        super(type.getItemId(), type.getId(), type.getName(), type.getPrice());
    }

    @Override
    public Object toDomain() {
        return new DesignType(getItemId(), getId(), getName(), getPrice());
    }
}
