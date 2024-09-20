package com.learnledger.utils;

import java.security.SecureRandom;

public class OTPGenerator {
    private String otp;
    
    public void generateOtp() {
        SecureRandom random = new SecureRandom();
        int otpValue = 100000 + random.nextInt(900000); 
        this.otp = String.valueOf(otpValue); 
    }
    
    public String getOtp() {
        if (otp != null) {
            return this.otp;
        } else {
            throw new IllegalStateException("OTP is null. You need to generate it first.");
        }
    }
}
