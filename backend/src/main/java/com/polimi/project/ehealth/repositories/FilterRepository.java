package com.polimi.project.ehealth.repositories;

import com.polimi.project.ehealth.entities.Application;

public interface FilterRepository {

    Application filterByDateOld(String app, String date);


}
