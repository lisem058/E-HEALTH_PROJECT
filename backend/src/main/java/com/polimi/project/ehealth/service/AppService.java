package com.polimi.project.ehealth.service;

import com.polimi.project.ehealth.entities.*;
import com.polimi.project.ehealth.repositories.AppRepository;
import com.polimi.project.ehealth.repositories.FilterRepositoryImpl;
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

    public List<DistinctAggregation> getDistinctApplications() {
        return appRepository.findDistinctApplications();
    }

    public List<ClassAppAggregation> getClassAppAggregation(String app) {return appRepository.findClassAppAggregation(app);}

    public List<DateAppAggregation> getDateAppAggregation(String app) {return appRepository.findDateAppAggregation(app);}

    public List<JournalAppAggregation> getJournalAppAggregation(String app) {return appRepository.findJournalAppAggregation(app);}

    public List<FullAggregation> filterByAppAndDate(String app, String date, int page, int size) {
        return appRepository.filterByDate(app, date, page, size, page * size);
    }

    public List<FullAggregation> filterByAppAndJournal(String app, String journal, int page, int size) {
        return appRepository.filterByJournal(app, journal, page, size, page * size);
    }

    public List<FullAggregation> filterByAppDateAndJournal(String app, String date, String journal, int page, int size) {
        return appRepository.filterByDateAndJournal(app, date, journal, page, size, page * size);
    }
}
