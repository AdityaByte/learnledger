package com.learnledger.repository;

import com.learnledger.enums.UserType;
import com.learnledger.model.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrganizationRepository extends MongoRepository<Organization, String>{
    
    @Query("{'credentials.email': ?0, 'credentials.password': ?1}")
    Organization findByCredentials(String email , String password);
    
    Long countByUserType(UserType userType);
    
    Boolean existsByPhoneNumber(String phoneNumber);
}
