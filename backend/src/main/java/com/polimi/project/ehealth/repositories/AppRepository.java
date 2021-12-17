package com.polimi.project.ehealth.repositories;

import com.polimi.project.ehealth.entities.*;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRepository extends MongoRepository<Application, String>, FilterRepository {

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
    FullAggregation findElementsByCategory(List<String> categories, int page, int size, int skip);

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

    @Aggregation(pipeline = {
            "{$match: {app: '?0'}}",
            "{$unwind: '$articles'}",
            "{$project: {'articles.class': 1}}",
            "{$group: {_id: '$articles.class', count: {$sum: 1}}}"
    })
    List<ClassAppAggregation> findClassAppAggregation(String app);


    @Aggregation(pipeline = {
            "{$match: {app: '?0'}}",
            "{$unwind: '$articles'}",
            "{$project: {'articles.publication_date': 1}}",
            "{$group: {_id: '$articles.publication_date', count: {$sum: 1}}}"
    })
    List<DateAppAggregation> findDateAppAggregation(String app);

    @Aggregation(pipeline = {
            "{$match: {app: '?0'}}",
            "{$unwind: '$articles'}",
            "{$project: {'articles.journal': 1}}",
            "{$group: {_id: '$articles.journal', count: {$sum: 1}}}",
            "{$sort: {count: -1}}",
            "{$limit: 5}"
    })
    List<JournalAppAggregation> findJournalAppAggregation(String app);

    @Aggregation(pipeline = {
            "{$match: {app: '?0'}}",
            "{$unwind: '$articles'}",
            "{$match: {'articles.publication_date': ?1}}",
            "{$facet: { \n" +
                    "'metadata': [ \n" +
                    "{$count: 'total'}, \n" +
                    "{$addFields: {page: ?2}} \n" +
                    "], \n" +
                    "'data': [{$skip: ?4}, {$limit: ?3}] \n" +
                    "}}"
    })
    List<FullAggregation> filterByDate(String app, String date, int page, int size, int skip);

    @Aggregation(pipeline = {
            "{$match: {app: '?0'}}",
            "{$unwind: '$articles'}",
            "{$match: {'articles.journal': ?1}}",
            "{$facet: { \n" +
                    "'metadata': [ \n" +
                    "{$count: 'total'}, \n" +
                    "{$addFields: {page: ?2}} \n" +
                    "], \n" +
                    "'data': [{$skip: ?4}, {$limit: ?3}] \n" +
                    "}}"
    })
    List<FullAggregation> filterByJournal(String app, String journal, int page, int size, int skip);

    @Aggregation(pipeline = {
            "{$match: {app: '?0'}}",
            "{$unwind: '$articles'}",
            "{$match: {'articles.publication_date': ?1}}",
            "{$match: {'articles.journal': ?2}}",
            "{$facet: { \n" +
                    "'metadata': [ \n" +
                    "{$count: 'total'}, \n" +
                    "{$addFields: {page: ?3}} \n" +
                    "], \n" +
                    "'data': [{$skip: ?5}, {$limit: ?4}] \n" +
                    "}}"
    })
    List<FullAggregation> filterByDateAndJournal(String app, String date, String journal, int page, int size, int skip);

    long count();
}