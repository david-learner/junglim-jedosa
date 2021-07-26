package com.jedosa.junglim.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Controller
public class FileController {

    private static final Logger log =  LoggerFactory.getLogger(FileController.class);
    private final StorageService storageService;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("files/upload")
    @ResponseBody
    public FilePathDto upload(@RequestParam("file") MultipartFile files) {
        String pathRemovedPrefix = FileStoragePathConverter.getExternalFilePath(storageService.store(files));
        log.debug("File uploaded in '{}'", pathRemovedPrefix);
        return new FilePathDto(pathRemovedPrefix);
    }

    // 외부에 노출되는 파일 경로는 application.properties에 명시된 파일 저장 경로 접두사를 지우고 files로 변경
    @GetMapping("files/{date}/{random}/{filename:.+}")
    public ResponseEntity serveImageFile(@PathVariable String date, @PathVariable String random, @PathVariable String filename) {
        String servedFilePath = FileStoragePathConverter.getInternalFilePath(Path.of(date, random, filename).toString());
        log.debug("Served file path: {}", servedFilePath);
        Resource file = storageService.loadAsResource(servedFilePath);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
