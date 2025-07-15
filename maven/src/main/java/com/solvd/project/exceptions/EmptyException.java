package com.solvd.project.exceptions;

public class EmptyException extends RuntimeException {
    public EmptyException(String message, Throwable cause) {
        super(message, cause);
    }

}