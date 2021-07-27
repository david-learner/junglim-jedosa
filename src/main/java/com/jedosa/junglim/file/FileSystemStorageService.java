package com.jedosa.junglim.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileSystemStorageService implements StorageService {

    private static final Logger log = LoggerFactory.getLogger(FileSystemStorageService.class);

    private Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties) {
        rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public String store(MultipartFile file) {

        createDirectory();
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        log.debug("Uploaded filename: {}", filename);
        Path storedFilePath = null;
        try {
            if (file.isEmpty()) {
                throw new IllegalStateException("Failed to store empty file " + filename);
            }
            // 파일경로에 상대경로 문자가 삽입된 경우 예외 발생
            if (filename.contains("..")) {
                // This is a security check
                throw new IllegalStateException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                // rootLocatoin뒤에 filename을 붙여준다
                storedFilePath = this.rootLocation.resolve(filename);
                Files.copy(inputStream, storedFilePath, StandardCopyOption.REPLACE_EXISTING);
                log.debug("Stored file path: {}", storedFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (storedFilePath == null) {
            throw new NullPointerException("File path is null");
        }
        return storedFilePath.toString();
    }

    @Override
    public void createDirectory() {
        try {
            // 경로 따라서 다수 디렉토리 생성이 안 됨
            log.debug("Root Location: '{}'", rootLocation);
            Path directories = Files.createDirectories(rootLocation);
            log.debug("Created directory path: {}", directories.toString());
        } catch (IOException e) {
            throw new IllegalStateException("Could not create directories");
        }
    }

    @Override
    public Path load(String filename) {
        return this.rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String fileUri) {
        log.debug("File path for loading: '{}'", fileUri);
        // Path를 Uri로 뽑을 때 어떤 형태로 뽑히는지 확인
        try {
            Resource resource = new UrlResource(Paths.get(fileUri).toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new IllegalStateException("Could not read file: " + fileUri);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Could not read file: " + fileUri);
        }
    }
}
