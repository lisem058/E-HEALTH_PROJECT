package com.polimi.project.ehealth.controller;

import com.polimi.project.ehealth.entities.Application;
import com.polimi.project.ehealth.service.AppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/app")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping
    public ResponseEntity<Application> getApplicationByName(@RequestParam(name = "name") String name) {
        return new ResponseEntity<>(appService.getApplicationByName(name), HttpStatus.OK);
    }

}
