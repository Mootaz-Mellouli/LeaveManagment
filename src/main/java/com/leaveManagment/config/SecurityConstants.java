package com.leaveManagment.config;

public class SecurityConstants {
    public static final String SECRET="secret";
    public static final long JWT_EXPIRATION = 432_000_000; //5 jours : 5 * 24 * 3600 = 432 000
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "authorization";
}
