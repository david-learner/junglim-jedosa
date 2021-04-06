package com.jedosa.junglim.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilePathDto {

    private String path;

    public FilePathDto(String path) {
        this.path = path;
    }
}
