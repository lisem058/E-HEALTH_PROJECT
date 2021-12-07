package com.polimi.project.ehealth.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

// Sub document
@Getter
@Setter
@AllArgsConstructor
public class Article {

    @Field("pubmed_id")
    private Long pubmedId;
    private String title;
    
    @Field("abstract")
    private String abstractText;

    @Field("class")
    private int classMark;

    @Field("publication_date")
    private String publicationDate;

    private String journal;

    private List<String> keywords;

    private List<Author> authors;

}
