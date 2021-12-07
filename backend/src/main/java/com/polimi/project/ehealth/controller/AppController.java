package com.polimi.project.ehealth.controller;

import com.polimi.project.ehealth.entities.*;
import com.polimi.project.ehealth.service.AppService;

import java.util.ArrayList;
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

        return ResponseEntity.ok(new ArrayList<>(appService.getDistinctCategories()));
    }

    @GetMapping(value = {"/api/v1/app"})
    @ResponseBody
    public ResponseEntity<List<DistinctAggregation>> getApplications() {
        return ResponseEntity.ok(new ArrayList<>(appService.getDistinctApplications()));
    }

    @GetMapping(value = {"/api/v1/analytics/class"})
    @ResponseBody
    public ResponseEntity<List<ClassAppAggregation>> getAnalyticsClassPerApp(@RequestParam(name = "app") String app) {
        return Optional
                .ofNullable(appService.getClassAppAggregation(app))
                .map(aggregation -> ResponseEntity.ok().body(aggregation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = {"/api/v1/analytics/date"})
    @ResponseBody
    public ResponseEntity<List<DateAppAggregation>> getAnalyticsDatePerApp(@RequestParam(name = "app") String app) {
        return Optional
                .ofNullable(appService.getDateAppAggregation(app))
                .map(aggregation -> ResponseEntity.ok().body(aggregation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = {"/api/v1/analytics/journal"})
    @ResponseBody
    public ResponseEntity<List<JournalAppAggregation>> getAnalyticsJorunalPerApp(@RequestParam(name = "app") String app) {
        return Optional
                .ofNullable(appService.getJournalAppAggregation(app))
                .map(aggregation -> ResponseEntity.ok().body(aggregation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = {"/api/v1/app/filter"})
    @ResponseBody
    public ResponseEntity<List<FullAggregation>> getAnalyticsDatePerApp(
            @RequestParam(name = "app") String app,
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "journal", required = false) String journal,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
            ) {

        if (page == null) {
            page = 0;
        }

        if (size == null) {
            size = 10;
        }

        if (journal == null && date != null) {
            return ResponseEntity.ok(appService.filterByAppAndDate(app, date, page, size));
        } else if (journal != null && date == null) {
            return ResponseEntity.ok(appService.filterByAppAndJournal(app, journal, page, size));
        } else if (journal != null) {
            return ResponseEntity.ok(appService.filterByAppDateAndJournal(app, date, journal, page, size));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
