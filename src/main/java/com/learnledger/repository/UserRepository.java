package com.learnledger.repository;

import com.learnledger.enums.UserType;
import com.learnledger.model.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String>{
    // You can make your own query methods
    User findByEmailAndPassword(String email , String password);
    
    Boolean existsByEmail(String email);
    
    User findByEmail(String email);
    
    Long countByUserType(UserType userType);
    
    @Query("{ 'userType': ?0 }")
    List<User> findAllUserByUserType(UserType userType);
}
