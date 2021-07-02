package com.jedosa.junglim.item.domain.option;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class PaperType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private String name;

    public PaperType(Long id, Long itemId, String name) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
    }
}
