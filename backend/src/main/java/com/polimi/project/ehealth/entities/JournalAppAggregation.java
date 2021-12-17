package com.polimi.project.ehealth.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@AllArgsConstructor
public class JournalAppAggregation {
    @Field("_id")
    private String journal;
    private Integer count;
}
