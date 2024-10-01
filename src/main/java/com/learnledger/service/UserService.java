package com.learnledger.service;

import com.learnledger.enums.UserType;
import com.learnledger.model.User;
import com.learnledger.repository.UserRepository;
import com.learnledger.security.PasswordEncoder;
import com.learnledger.security.SaltGenerator;
import com.learnledger.utils.CredentialsGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    
    @Autowired
    private UserRepository repository;
    
    private final PasswordEncoder passwordEncoder = new PasswordEncoder();
    private final SaltGenerator saltGenerator = new SaltGenerator();
    private final CredentialsGenerator cg = new CredentialsGenerator();
    
    public User saveUser(User user) throws NoSuchAlgorithmException{
        user.setId(cg.generateIdForUser());
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
    
    public Long countByUserType(UserType userType) {
        return repository.countByUserType(userType);
    }
    
    public List<User> findAllUserByUserType(UserType userType){
        return (List<User>) repository.findAllUserByUserType(userType);
    }
}