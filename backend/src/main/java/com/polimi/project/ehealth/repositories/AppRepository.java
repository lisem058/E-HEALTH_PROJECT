package com.polimi.project.ehealth.repositories;

import com.polimi.project.ehealth.entities.AggregationApp;
import com.polimi.project.ehealth.entities.Application;
import com.polimi.project.ehealth.entities.DistinctAggregation;
import com.polimi.project.ehealth.entities.FullAggregation;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRepository extends MongoRepository<Application, String> {

    @Query("{app: '?0'}")
    Application findAppByName(String name);

    // db.e_health_pubmed.aggregate([{$unwind: "$articles"}, {$match: {"articles.keywords": {$all: ["stroke"] }}}])
    @Aggregation(pipeline = {"{$unwind: '$articles'}",
                            "{$match: {'articles.keywords': {$all: ?0}}}",
                            "{$facet: { \n" +
                                "'metadata': [ \n" +
                                    "{$count: 'total'}, \n" +
                                    "{$addFields: {page: ?1}} \n" +
                                "], \n" +
                                "'data': [{$skip: ?3}, {$limit: ?2}] \n" +
                            "}}"})
    List<FullAggregation> findElementsByCategory(List<String> categories, int page, int size, int skip);

    @Aggregation(pipeline = {
                    "{$unwind: '$articles'}",
                    "{$project: {\n" +
                        "_id: 0,\n" +
                        "keys: '$articles.keywords'\n" +
                    "}}",
                    "{$group: {\n" +
                        "_id: null,\n" +
                        "uniqueKeys: {$push: '$keys'}\n" +
                    "}}",
                    "{$project: {\n" +
                        "selectedKeys: {\n" +
                            "$reduce: {\n" +
                                "input: '$uniqueKeys',\n" +
                                "initialValue: [],\n" +
                                "in: {$setUnion: ['$$value', '$$this']}\n" +
                            "}\n" +
                        "}\n" +
                    "}}"
                    })
    List<String> findDistinctCategories();

    @Aggregation(pipeline = {
            "{$project: {\n" +
                    "_id: 0,\n" +
                    "keys: '$app'\n" +
                    "}}",
            "{$group: {\n" +
                    "_id: null,\n" +
                    "uniqueKeys: {$push: '$keys'}\n" +
                    "}}",
    })
    List<DistinctAggregation> findDistinctApplications();

    long count();
}
