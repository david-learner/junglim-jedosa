package com.jedosa.junglim.item.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    Long id;
    @Column(name = "item_name", nullable = false)
    String name;
    Long price; // 기본 가격을 측정할 수 없는 상품들을 고려하여 nullable true
}
