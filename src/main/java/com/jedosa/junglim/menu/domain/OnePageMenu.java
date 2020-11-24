package com.jedosa.junglim.menu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class OnePageMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;

    public OnePageMenu(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }
}