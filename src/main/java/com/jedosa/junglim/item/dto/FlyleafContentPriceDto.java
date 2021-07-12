package com.jedosa.junglim.item.dto;

import com.jedosa.junglim.item.domain.option.FlyleafColorType;
import com.jedosa.junglim.item.domain.option.FlyleafContentPrice;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FlyleafContentPriceDto extends BaseItemOptionDto implements Domainable {

    public FlyleafContentPriceDto(Long itemId, Long id, String name, BigDecimal price) {
        super(itemId, id, name, price);
    }

    public FlyleafContentPriceDto(FlyleafContentPrice type) {
        super(type.getItemId(), type.getId(), type.getName(), type.getPrice());
    }

    @Override
    public Object toDomain() {
        return new FlyleafContentPrice(getItemId(), getId(), getName(), getPrice());
    }
}
