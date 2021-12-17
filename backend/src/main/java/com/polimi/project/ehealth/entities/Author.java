package com.polimi.project.ehealth.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
public class Author {

    @Field("lastname")
    private String lastName;

    @Field("firstname")
    private String firstName;

    private String initials;
    private String affiliation;

}
