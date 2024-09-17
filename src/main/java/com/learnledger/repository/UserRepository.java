package com.learnledger.repository;

import com.learnledger.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    // You can make your own query methods
    User findByEmailAndPassword(String email , String password);
    
    Boolean existsByEmail(String email);
    
    User findByEmail(String email);
}
