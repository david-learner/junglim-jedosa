package com.jedosa.junglim.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Random;

@Configuration
@ConfigurationProperties("storage")
public class StorageProperties {

    private static final Logger log =  LoggerFactory.getLogger(StorageProperties.class);

    private static final String SLASH = "/";
    @Value("${file.upload.path}")
    private String fileUploadPathPrefix;
    private String location;

    public StorageProperties() {

    }

    @PostConstruct
    private void generateFilePath() {
        long randomNumber = Math.abs(new Random().nextLong());
        String todayDate = LocalDate.now().toString().replaceAll("-", "");
        String randomNumberFilePath = Paths.get(todayDate, String.valueOf(randomNumber)).toString();
        // Paths.get(fileUploadPathPrefix, randomNumberFilePath).toString()
        // C드라이브 밑에 바로 저장된다
        // application.yml에서 가져온 경로는 / 로 시작된다. 즉
        // 절대경로 시작되는 것이다. 반대로 /를 제거하면 상대경로가 되며 해당 프로젝트 폴더를 루트로 잡는다
        this.location = Paths.get(fileUploadPathPrefix, randomNumberFilePath).toString();
        log.debug("Generated path for storing file: " + location);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
