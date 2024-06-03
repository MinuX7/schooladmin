package com.cosmind.schooladmin.service;

import com.cosmind.schooladmin.dto.FileResponseDto;
import com.cosmind.schooladmin.model.FileType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${configuration.base-storage}")
    private String baseStorage;
    @Value("${configuration.profilePicture.default-avatar}")
    private String defaultTeacherAvatar;

    public String saveProfilePicture(String fileName, String imageData)  {
        if (imageData == null || imageData.isBlank() ) {
            logger.error("Emty profile picture provided. Using default avatar", defaultTeacherAvatar);
            return defaultTeacherAvatar;
        }
        try {
            String base64Image = imageData.split(",")[1];
            String profilePictureDir = baseStorage + File.separator + "profile-pictures";
            File profilePictureDir1 = new File(profilePictureDir);
            if (!profilePictureDir1.exists()) {
                profilePictureDir1.mkdirs();
            }
            File profilePictureFile = new File(profilePictureDir, fileName);
            if (profilePictureFile.exists()) {
                String extension = FilenameUtils.getExtension(fileName);
                fileName = FilenameUtils.removeExtension(fileName) + "_" +  System.currentTimeMillis();
                if (extension != null && !extension.isEmpty()) {
                    fileName = fileName + "." + extension;
                }
                profilePictureFile = new File(profilePictureDir, fileName);
            }
            byte[] decodedImage = Base64.getDecoder().decode(base64Image);
            FileUtils.writeByteArrayToFile(profilePictureFile, decodedImage);
            logger.info("Successfully saved profile picture file {}.", fileName);
            return fileName;
        } catch (Exception ex) {
            logger.error("Could not save profile picture file.", fileName);
            return defaultTeacherAvatar;
        }
    }

    public FileResponseDto getProfilePicture(String fileName)  {

        String profilePicture = baseStorage + File.separator + "profile-pictures" + File.separator + fileName;
        logger.info("Fetching profile file from {}", profilePicture);
        File profilePictureFile = new File(profilePicture);
        try {
            byte[] data = IOUtils.toByteArray(new FileInputStream(profilePictureFile));
            FileType fileType = FileType.fromFileName(fileName);
            return new FileResponseDto(data, fileType);
        } catch (IOException e) {
            logger.error("Could not read profile picture {}", fileName, e);
            return null;
        }
    }


}
