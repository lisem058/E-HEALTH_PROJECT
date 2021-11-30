package com.polimi.project.ehealth.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("e_health_pubmed")
@Getter
@Setter
@AllArgsConstructor
public class AggregationApp {

    @Id
    private String id;

    private String app;

    @Field("articles")
    private Article article;

}
