package com.polimi.project.ehealth.controller;

import com.polimi.project.ehealth.dao.ApplicationDaoImpl;
import com.polimi.project.ehealth.entities.Application;
import com.polimi.project.ehealth.entities.FullAggregation;
import com.polimi.project.ehealth.service.AppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping(value = {"/api/v1/app/search"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Application> getApplicationByName(@RequestParam(name = "app") String name) {
        return new ResponseEntity<>(appService.getApplicationByName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/category/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<FullAggregation>> getAppsByCategory(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestBody List<String> categories
    ) {
        if (page == null) {
            page = 0;
        }

        if (size == null) {
            size = 10;
        }

        return new ResponseEntity<>(appService.getByCategories(categories, page, size), HttpStatus.OK);
    }

    @GetMapping(value = {"/api/v1/category"})
    public List<String> getDistinctCategories() {
        return new ApplicationDaoImpl().findByField();
    }
}
