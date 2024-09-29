package com.learnledger.service;

import com.learnledger.enums.UserType;
import com.learnledger.model.Credentials;
import com.learnledger.model.File;
import com.learnledger.model.Organization;
import com.learnledger.repository.OrganizationRepository;
import com.learnledger.utils.Base64Converter;
import com.learnledger.utils.CredentialsGenerator;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OrganizationService {
    
    @Autowired
    private OrganizationRepository repository;
    
    public Credentials saveOrganization(Organization organization){
        CredentialsGenerator credentialsGenerator = new CredentialsGenerator();
        Credentials credentials = new Credentials();
        
        credentials.setEmail(credentialsGenerator.generateEmailForOrganization(organization.getOrganizationName()));
        credentials.setPassword(credentialsGenerator.generatePasswordForOrganization(organization.getOrganizationName()));
        
        organization.setOrganizationId(credentialsGenerator.generateIdForOrganization(organization.getOrganizationName()));
        organization.setCredentials(credentials);
        
        repository.save(organization);
        
        return credentials;
    }
    
    public Organization findByCredentials(String email , String password){
        return repository.findByCredentials(email, password);
    }
    
    public Long countByUserType(UserType userType){
        return repository.countByUserType(userType);
    }
}
