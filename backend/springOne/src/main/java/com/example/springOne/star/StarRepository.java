package com.example.springOne.star;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StarRepository extends MongoRepository<Star, String> {

    Optional<Star> findStarByUsername(String username);
    Optional<Star> findStarByEmail(String email);

    Optional<Star> findStarById(String id);
}
