package com.jedosa.junglim.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileStoragePathConverter {

    private static final Logger log = LoggerFactory.getLogger(FileStoragePathConverter.class);

    private static String fileUploadPathPrefix;
    private static final String FILE_SEPARATOR = File.separator;

    public static String getExternalFilePath(String storedPath) {
        String externalPrefix = FILE_SEPARATOR + "files";
        String pathWithoutPrefix = removePrefixFromStoredPath(storedPath);
        String externalFileUrl = externalPrefix + pathWithoutPrefix;
        log.debug("external file url: '{}'", externalFileUrl);
        return externalFileUrl;
    }

    public static String getInternalFilePath(String path) {

        return fileUploadPathPrefix + FILE_SEPARATOR + path;
    }

    public static String removePrefixFromStoredPath(String storedPath) {
        log.debug("file upload path prefix: '{}'", fileUploadPathPrefix);
        log.debug("stored path: '{}'", storedPath);
        String pathRemovedPrefix = storedPath.replace(fileUploadPathPrefix, "");
        log.debug("pathRemovedPrefix: '{}'", pathRemovedPrefix);
        return pathRemovedPrefix;
    }

    @Value("${file.upload.path}")
    public void setFileUploadPathPrefixStatic(String path) {
        FileStoragePathConverter.fileUploadPathPrefix = path;
    }

}
