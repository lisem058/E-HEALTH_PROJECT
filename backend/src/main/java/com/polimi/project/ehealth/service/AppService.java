package com.polimi.project.ehealth.service;

import com.polimi.project.ehealth.entities.Application;
import com.polimi.project.ehealth.repositories.AppRepository;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    private final AppRepository appRepository;

    public AppService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public Application getApplicationByName(String name) {
        return appRepository.findAppByName(name);
    }
}
