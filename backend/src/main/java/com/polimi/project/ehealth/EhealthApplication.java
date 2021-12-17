package com.polimi.project.ehealth;

import com.polimi.project.ehealth.repositories.AppRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class EhealthApplication {

	final AppRepository appRepository;

	public EhealthApplication(AppRepository appRepository) {
		this.appRepository = appRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(EhealthApplication.class, args);
	}

}
