package com.cosmind.schooladmin.model;

import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;

import java.util.List;

public enum FileType {
    JPEG( List.of("jpg", "jpeg"), MediaType.IMAGE_JPEG ),
    PNG ("png", MediaType.IMAGE_PNG),
    GIF ("gif", MediaType.IMAGE_GIF),
    DEFAULT_FILE_TYPE ( "", MediaType.APPLICATION_OCTET_STREAM);

    private List<String> extenstions;
    @Getter
    private MediaType mediaType;

    private FileType(List<String> extentions, MediaType mediaType) {
        this.extenstions = extentions;
        this.mediaType = mediaType;
    }

    private FileType(String extention, MediaType mediaType) {
        this(List.of(extention), mediaType);
    }

    public static FileType fromFileName(String fileName) {
        String extenstion = FilenameUtils.getExtension(fileName);
        for (FileType fileType: values()) {
            for (String ftExt: fileType.extenstions) {
                if (extenstion.equalsIgnoreCase(ftExt)) {
                    return fileType;
                }
            }
        }
        return DEFAULT_FILE_TYPE;
    }
}
