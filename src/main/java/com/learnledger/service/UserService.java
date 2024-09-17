package com.learnledger.service;

import com.learnledger.model.User;
import com.learnledger.repository.UserRepository;
import com.learnledger.security.PasswordEncoder;
import com.learnledger.security.SaltGenerator;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    
    @Autowired
    private UserRepository repository;
    
    private final PasswordEncoder passwordEncoder = new PasswordEncoder();
    private final SaltGenerator saltGenerator = new SaltGenerator();
    
    public User saveUser(User user) throws NoSuchAlgorithmException{
        String rawPassword = user.getPassword();
        if(rawPassword != null){
            saltGenerator.generateSalt();
            passwordEncoder.encodePassword(rawPassword , saltGenerator.getSalt());
        }
        if(passwordEncoder.getHashedPassword() != null && saltGenerator.getSalt() != null){
            user.setPassword(passwordEncoder.getHashedPassword());
            user.setSalt(saltGenerator.getSalt());
        }
        return repository.save(user);
    }
    
    public User findByEmailAndPassword(String email, String rawPassword) throws NoSuchAlgorithmException{
        User user = repository.findByEmail(email);
        passwordEncoder.encodePassword(rawPassword, user.getSalt());
        Boolean bool = user.getPassword().equals(passwordEncoder.getHashedPassword());
        if(bool){
            return user;
        }
        else{
            return null;
        }
    }
    
    public boolean checkEmailExists(String email){
        return repository.existsByEmail(email);
    }
}