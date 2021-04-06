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
import java.nio.file.Paths;

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
        String store = storageService.store(files);
        return new FilePathDto(store);
    }

    // uploaded-files(디렉토리명) 숨기고 외부에 노출되는 주소는 files/date/random/filename 으로
    @GetMapping("/uploaded-files/{date}/{random}/{filename:.+}")
    public ResponseEntity serveImageFile(@PathVariable String date, @PathVariable String random, @PathVariable String filename) {
        Path filePath = Paths.get("uploaded-files", date, random, filename);
        // filePath가 개판임
        Resource file = storageService.loadAsResource(filePath.toString());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
