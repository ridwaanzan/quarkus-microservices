package com.codecipta.helper;

public class GenerateId {
    public static String generateUUID() {
        // Generate random UUID
        return java.util.UUID.randomUUID().toString();
    }
}
