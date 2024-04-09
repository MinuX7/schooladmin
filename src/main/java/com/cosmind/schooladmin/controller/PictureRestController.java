package com.cosmind.schooladmin.controller;

import com.cosmind.schooladmin.dto.FileResponseDto;
import com.cosmind.schooladmin.service.FileService;
import com.cosmind.schooladmin.service.TeacherService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/public/profile-picture")
@CrossOrigin(origins="*")
public class PictureRestController {

    @Autowired
    FileService fileService;

    @GetMapping("/{teacherProfilePicture}")
    public ResponseEntity<byte[]> getTeacherProfile(@PathVariable String teacherProfilePicture) {
        MDC.put("requestId", UUID.randomUUID().toString());

        FileResponseDto fileResponse = fileService.getProfilePicture(teacherProfilePicture);
        if (fileResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.setContentType(fileResponse.getFileType().getMediaType());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(fileResponse.getData(), headers, HttpStatus.OK);
        return responseEntity;
    }


}
