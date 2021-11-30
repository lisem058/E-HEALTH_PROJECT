package com.polimi.project.ehealth.service;

import com.polimi.project.ehealth.entities.AggregationApp;
import com.polimi.project.ehealth.entities.Application;
import com.polimi.project.ehealth.entities.FullAggregation;
import com.polimi.project.ehealth.repositories.AppRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    private final AppRepository appRepository;

    public AppService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public Application getApplicationByName(String name) {
        return appRepository.findAppByName(name);
    }

    public List<FullAggregation> getByCategories(List<String> categories, int page, int size) {
        return appRepository.findElementsByCategory(categories, page, size, page * size);
    }

    public List<String> getDistinctCategories() {
        return appRepository.findDistinctCategories();
    }
}
