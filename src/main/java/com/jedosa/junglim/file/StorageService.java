package com.jedosa.junglim.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    String store(MultipartFile file);

    void createDirectory();

    Path load(String filename);

    Resource loadAsResource(String uri);
}
