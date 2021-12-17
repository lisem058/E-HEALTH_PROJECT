package com.polimi.project.ehealth.dto;

import com.polimi.project.ehealth.entities.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationDto {
    private String id;

    private String app;

    private List<ArticleDto> articles;

}
