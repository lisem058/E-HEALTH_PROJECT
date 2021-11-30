package com.polimi.project.ehealth.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//Document("e_health_pubmed")
@Getter
@Setter
@AllArgsConstructor
public class FullAggregation {

    List<Metadata> metadata;
    List<AggregationApp> data;

}
