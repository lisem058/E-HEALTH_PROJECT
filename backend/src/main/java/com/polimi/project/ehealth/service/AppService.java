package com.polimi.project.ehealth.service;

import com.polimi.project.ehealth.dto.ApplicationDto;
import com.polimi.project.ehealth.dto.ArticleDto;
import com.polimi.project.ehealth.dto.DateAppAggregationDto;
import com.polimi.project.ehealth.entities.*;
import com.polimi.project.ehealth.repositories.AppRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppService {

    private final AppRepository appRepository;

    public AppService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public ApplicationDto getApplicationByName(String name) {

        Application app =  appRepository.findAppByName(name);

        List<ArticleDto> articleDtos = new ArrayList<>();

        for (Article article : app.getArticles()) {
            String classMark;
            switch (article.getClassMark()) {
                case 2: classMark = "RCT"; break;
                case 3: classMark = "Systematic Review"; break;
                case 4: classMark = "MetaAnalysis"; break;
                default: classMark = "Observational Study"; break;
            }
            articleDtos.add(
                    new ArticleDto(
                            article.getPubmedId(),
                            article.getTitle(),
                            article.getAbstractText(),
                            classMark,
                            article.getPublicationDate(),
                            article.getJournal(),
                            article.getKeywords(),
                            article.getAuthors()
                    )
            );
        }

        return new ApplicationDto(
                app.getId(),
                app.getApp(),
                articleDtos
        );
    }

    public FullAggregation getByCategories(List<String> categories, int page, int size) {
        return appRepository.findElementsByCategory(categories, page, size, page * size);
    }

    public List<String> getDistinctCategories() {
        return appRepository.findDistinctCategories();
    }

    public List<DistinctAggregation> getDistinctApplications() {
        return appRepository.findDistinctApplications();
    }

    public List<ClassAppAggregation> getClassAppAggregation(String app) {
        List<ClassAppAggregation> classAppAggregations = appRepository.findClassAppAggregation(app);
        List<ClassAppAggregation> classAppAggregationList = new ArrayList<>();
        for (ClassAppAggregation classAppAggregation1 : classAppAggregations) {
            String classMark;
            switch (classAppAggregation1.getClassName()) {
                case "2": classMark = "RCT"; break;
                case "3": classMark = "Systematic Review"; break;
                case "4": classMark = "MetaAnalysis"; break;
                default: classMark = "Observational Study"; break;
            }
            classAppAggregationList.add(
                    new ClassAppAggregation(
                            classMark,
                            classAppAggregation1.getCount()
                    )
            );
        }
        return classAppAggregationList;
    }

    public List<DateAppAggregationDto> getDateAppAggregation(String app) throws ParseException {
        List<DateAppAggregation> dateAppAggregations = appRepository.findDateAppAggregation(app);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<DateAppAggregationDto> dateAppAggregationDtos = new ArrayList<>();
        for (DateAppAggregation d : dateAppAggregations) {
            dateAppAggregationDtos.add(
              new DateAppAggregationDto(
                      (int)(sdf.parse(d.getDate()).getTime() / 1000),
                      d.getCount()
              )
            );
        }

        return  dateAppAggregationDtos;
    }

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