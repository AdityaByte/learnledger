package com.learnledger.utils;

import java.security.SecureRandom;

public class CredentialsGenerator {
    
    SecureRandom sr = new SecureRandom();
    int id = 1000 + sr.nextInt(9999);
    
    public String generateIdForOrganization(String orgName){
        String newString = orgName.replaceAll("[^a-zA-Z]", "");
        newString.toLowerCase();
        System.out.println("new String = > " + newString);
        StringBuilder sb = new StringBuilder();
        sb.append(newString);
        sb.append(id);
        return sb.toString();
    }
    
    public String generateEmailForOrganization(String orgName){
        String newString = orgName.replaceAll("[^a-zA-Z]", "");
        newString.toLowerCase();
        StringBuilder sb = new StringBuilder();
        sb.append(newString);
        sb.append("@learnledger.in");
        return sb.toString();
    }
    
    public String generatePasswordForOrganization(String orgName){
        String newString = orgName.replaceAll("[^a-zA-Z]", "");
        newString.toLowerCase();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890#@$%&";
        StringBuilder sb = new StringBuilder();
        sb.append(newString);
        for(int i = 0; i<5; i++){
            sb.append(chars.charAt(sr.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
