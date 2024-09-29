package com.learnledger.utils;

import java.util.Base64;

public class Base64Converter {
    
    public static String convertBinaryToBase64(byte[] binaryData) {
        return Base64.getEncoder().encodeToString(binaryData);
    }
}
