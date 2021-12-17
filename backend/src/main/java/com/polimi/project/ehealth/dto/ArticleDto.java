package com.polimi.project.ehealth.dto;

import com.polimi.project.ehealth.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ArticleDto {
    private Long pubmedId;
    private String title;

    private String abstractText;

    private String classMark;

    private String publicationDate;

    private String journal;

    private List<String> keywords;

    private List<Author> authors;
}
