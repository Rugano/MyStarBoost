package com.example.springOne.project;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.springOne.project")
public class ProjectsMongoConfig {

    @Bean
    public MongoClient projectsMongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/my_star_boost_db");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(settings);
    }

    @Bean
    public MongoTemplate projectsMongoTemplate() {
        return new MongoTemplate(projectsMongoClient(), "my_star_boost_db");
    }
}
