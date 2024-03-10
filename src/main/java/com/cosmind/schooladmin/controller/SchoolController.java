package com.cosmind.schooladmin.controller;

import com.cosmind.schooladmin.client.ExternalService;
import com.cosmind.schooladmin.model.School;
import com.cosmind.schooladmin.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/schools")
@CrossOrigin
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private ExternalService externalService;

    @GetMapping
    public List<School> getSchools() {
        return schoolRepository.findAll();
    }

    @GetMapping(value = "/external", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getExternalData() {
        return externalService.getData();
    }

    @PostMapping
    public School saveSchool(@RequestBody School school) {
        return schoolRepository.save(school);
    }
}
