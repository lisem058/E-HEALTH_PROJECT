package com.polimi.project.ehealth.dao;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;
import com.polimi.project.ehealth.entities.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.print.attribute.standard.JobKOctets;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {

    private static final String COLLECTION = "e_health_pubmed";

    @Autowired
    MongoTemplate mongoTemplate;

    public List<String> queryDistinctCategory() {
        List<String> categoryList = new ArrayList<>();
        DistinctIterable<String> distinctIterable = mongoTemplate.getCollection(COLLECTION).distinct("articles.keywords", String.class);

        for (String category : distinctIterable) {
            categoryList.add(category);
        }

        return categoryList;
    }

    //@Override
    public List<String> findByField() {

        System.out.println(mongoTemplate.getCollection("e_health_pubmed"));
        return null;
    }

}
