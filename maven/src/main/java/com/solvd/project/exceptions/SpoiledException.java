package com.solvd.project.exceptions;

public class SpoiledException extends RuntimeException {
    public SpoiledException(String message, Throwable cause) {
        super(message, cause);
    }

}