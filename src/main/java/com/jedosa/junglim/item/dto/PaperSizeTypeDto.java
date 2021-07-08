package com.jedosa.junglim.item.dto;

import com.jedosa.junglim.item.domain.option.PaperSizeType;
import com.jedosa.junglim.item.domain.option.PaperType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class PaperSizeTypeDto implements Domainable {

    private Long id;
    private Long itemId;
    private String name;

    public PaperSizeTypeDto(PaperSizeType type) {
        this.id = type.getId();
        this.itemId = type.getItemId();
        this.name = type.getName();
    }

    @Override
    public Object toDomain() {
        return new PaperSizeType(id, itemId, name);
    }
}
