package com.example.springOne.star;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.Month.FEBRUARY;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.springOne.star")
public class MongoConfig {

    @Bean
    CommandLineRunner commandLineRunner (StarRepository starRepository){
     return args -> {
         Star gur = new Star(
                 "Gur",
                 "Shmuelevitz",
                 "gur_shmu@hotmail.com",
                 LocalDate.of(1999, FEBRUARY, 11)
         );

         Star alex = new Star(
                 "Alex",
                 "Shmuelevitz",
                 "alex_shmu@hotmail.com",
                 LocalDate.of(1989, FEBRUARY, 11)
         );
         List<Star> starList = new ArrayList<>();
         starList.add(gur);
         starList.add(alex);
         starRepository.saveAll(starList);
     };
    }
    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/my_star_boost_db");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(settings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "my_star_boost_db");
    }
}
