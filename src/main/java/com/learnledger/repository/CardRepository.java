package com.learnledger.repository;

import com.learnledger.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String>{
    
}
