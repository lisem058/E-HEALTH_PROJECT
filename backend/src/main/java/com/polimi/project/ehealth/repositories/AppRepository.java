package com.polimi.project.ehealth.repositories;

import com.polimi.project.ehealth.entities.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends MongoRepository<Application, String> {

    @Query("{app: '?0'}")
    Application findAppByName(String name);

    public long count();
}
