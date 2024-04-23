package org.example.be_java_hisp_w26_g07.exception;

public class BadRequestExeception extends RuntimeException {
    public BadRequestExeception(String message) {
        super(message);
    }
}
