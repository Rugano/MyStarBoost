package com.example.springOne.star;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends MongoRepository<Star, Long> {

}
