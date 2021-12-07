package com.polimi.project.ehealth.repositories;

import com.polimi.project.ehealth.entities.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class FilterRepositoryImpl implements FilterRepository{

    @Autowired
    MongoTemplate mongoTemplate;

    @Deprecated
    @Override
    public Application filterByDateOld(String app, String date) {
        Query query = Query.query(
          Criteria.where("app").is(app)
        ).addCriteria(
                Criteria.where("articles").elemMatch(Criteria.where("publication_date").is(date))
        );

        return mongoTemplate.find(query, Application.class).get(0);
    }
}
