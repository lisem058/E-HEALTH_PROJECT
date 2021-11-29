package com.polimi.project.ehealth.dao;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.polimi.project.ehealth.entities.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao{

    private static final String COLLECTION = "e_health_pubmed";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<String> findByField(String field) {
//        return mongoTemplate.getCollection(COLLECTION).distinct(field);
        return null;
    }

    public List<String> queryDistinctCategory() {
        List<String> categoryList = new ArrayList<>();
        MongoCollection mongoCollection = mongoTemplate.getCollection(COLLECTION);
        DistinctIterable distinctIterable = mongoCollection.distinct("article.keywords", String.class);
        MongoCursor cursor = distinctIterable.iterator();
        while (cursor.hasNext()) {
            String category = (String)cursor.next();
            categoryList.add(category);
        }
        return categoryList;
    }
}
