package com.learnledger.service;

import com.learnledger.model.User;
import com.learnledger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    
    @Autowired
    private UserRepository repository;
    
    public User saveUser(User user){
        return repository.save(user);
    }
    
    public User findByEmailAndPassword(String email, String password){
        return repository.findByEmailAndPassword(email, password);
    }
    
}