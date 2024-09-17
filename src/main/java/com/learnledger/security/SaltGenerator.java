package com.learnledger.security;

import java.security.SecureRandom;
import java.util.Base64;

public class SaltGenerator {
    
    private String salt;
              
    public void generateSalt(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] b1 = new byte[16];
        secureRandom.nextBytes(b1);
        this.salt = Base64.getEncoder().encodeToString(b1);
    }
    
    
    public String getSalt(){
        if(this.salt != null){
            return this.salt;
        }
        else{
            throw new NullPointerException("salt is null");
        }
    }
    
}
