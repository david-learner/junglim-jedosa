package com.jedosa.junglim.item.dto;

import com.jedosa.junglim.item.domain.option.CoatingType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CoatingTypeDto extends BaseItemOptionDto implements Domainable{

    public CoatingTypeDto(Long itemId, Long id, String name, BigDecimal price) {
        super(itemId, id, name, price);
    }

    public CoatingTypeDto(CoatingType coatingType) {
        super(coatingType.getItemId(), coatingType.getId(), coatingType.getName(), coatingType.getPrice());
    }

    @Override
    public Object toDomain() {
        return new CoatingType(getItemId(), getId(), getName(), getPrice());
    }
}
