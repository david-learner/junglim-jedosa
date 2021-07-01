package com.jedosa.junglim.item.dto;

import com.jedosa.junglim.item.domain.option.FlyleafColorType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FlyleafColorTypeDto extends BaseItemOptionDto implements Domainable {

    public FlyleafColorTypeDto(Long itemId, Long id, String name, BigDecimal price) {
        super(itemId, id, name, price);
    }

    public FlyleafColorTypeDto(FlyleafColorType type) {
        super(type.getItemId(), type.getId(), type.getName(), type.getPrice());
    }

    @Override
    public Object toDomain() {
        return new FlyleafColorType(getItemId(), getId(), getName(), getPrice());
    }
}
