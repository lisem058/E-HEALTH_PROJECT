package com.polimi.project.ehealth.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("e_health_pubmed")
public class Application {

    @Id
    private String id;

    private String app;

    private List<Article> articles;

    public Application(String id, String app, List<Article> articles) {
        this.id = id;
        this.app = app;
        this.articles = articles;
    }
}
