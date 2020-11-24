package com.jedosa.junglim.menu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OnePageMenuDto {

    private Long id;
    private String content;

    public OnePageMenuDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public OnePageMenu toPage() {
        if (id == null) {
            throw new IllegalStateException("페이지 ID가 존재하지 않습니다");
        }
        return new OnePageMenu(id, content);
    }
}
