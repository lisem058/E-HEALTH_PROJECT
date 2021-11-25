package com.polimi.project.ehealth.entities;


import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

// Sub document
public class Article {

    @Field("pubmed_id")
    private Long pubmedId;
    private String title;
    
    @Field("abstract")
    private String abstractText;

    @Field("class")
    private int classMark;

    private List<String> keywords;

    public Article(Long pubmedId, String title, String abstractText, int classMark, List<String> keywords) {
        this.pubmedId = pubmedId;
        this.title = title;
        this.abstractText = abstractText;
        this.keywords = keywords;
    }
}
