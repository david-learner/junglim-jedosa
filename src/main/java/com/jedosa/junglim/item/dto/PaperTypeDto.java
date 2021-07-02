package com.jedosa.junglim.item.dto;

import com.jedosa.junglim.item.domain.option.PaperType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class PaperTypeDto implements Domainable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private String name;

    public PaperTypeDto(PaperType type) {
        this.id = type.getId();
        this.itemId = type.getItemId();
        this.name = type.getName();
    }

    @Override
    public Object toDomain() {
        return new PaperType(id, itemId, name);
    }
}
