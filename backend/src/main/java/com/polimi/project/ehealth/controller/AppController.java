package com.polimi.project.ehealth.controller;

import com.polimi.project.ehealth.entities.Application;
import com.polimi.project.ehealth.entities.DistinctAggregation;
import com.polimi.project.ehealth.entities.FullAggregation;
import com.polimi.project.ehealth.service.AppService;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping(value = {"/api/v1/app/search"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Application> getApplicationByName(@RequestParam(name = "app") String name) {
        return Optional
                .ofNullable(appService.getApplicationByName(name))
                .map(application -> ResponseEntity.ok().body(application))
                .orElseGet(() -> ResponseEntity.notFound().build());
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

        return Optional
                .ofNullable(appService.getByCategories(categories, page, size))
                .map(aggregation -> ResponseEntity.ok().body(aggregation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = {"/api/v1/category"})
    @ResponseBody
    public ResponseEntity<List<String>> getDistinctCategories() {

        return ResponseEntity.ok(appService.getDistinctCategories().stream().collect(Collectors.toList()));
    }

    @GetMapping(value = {"/api/v1/app"})
    @ResponseBody
    public ResponseEntity<List<DistinctAggregation>> getApplications() {
        return ResponseEntity.ok(appService.getDistinctApplications().stream().collect(Collectors.toList()));
    }
}
