package com.cosmind.schooladmin.controller;

import com.cosmind.schooladmin.model.School;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/version")
@CrossOrigin
public class VersionController {

    @Value("${project.version}")
    private String projectVersion;
    @GetMapping
    public String getSchools() {
        return projectVersion;
    }
}
