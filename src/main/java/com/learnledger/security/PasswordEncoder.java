package com.learnledger.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    
    private String hashedPassword;
    
    public void encodePassword(String rawPassword , String salt) throws NoSuchAlgorithmException{
        
        String combinedPassword = rawPassword + salt;
        
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        
        byte[] b1 = messageDigest.digest(combinedPassword.getBytes());
        
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : b1){
            stringBuilder.append(String.format("%02x", b));
        }
        
        hashedPassword = stringBuilder.toString();
    }
    
    public String getHashedPassword(){
        if(hashedPassword != null){
            return hashedPassword;
        }
        else{
            throw new NullPointerException("hashed password is null");
        }
    }
    
    
}
