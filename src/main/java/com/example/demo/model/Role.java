package com.example.demo.model;

public class Role {
    public static final String ADMIN = "ADMIN";
    public static final String ANALYST = "ANALYST";
    public static final String MANAGER = "MANAGER";
    
    // Private constructor to prevent instantiation if just using constants,
    // or you can make this a Java Enum:
    // public enum Role { ADMIN, ANALYST, MANAGER }
    // But the Entity 'AppUser' treats role as a String in the provided code.
    // So we keep it as constants for simplicity with the 'AppUser' string field.
}