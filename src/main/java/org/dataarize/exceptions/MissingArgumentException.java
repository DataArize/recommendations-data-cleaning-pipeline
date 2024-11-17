package org.dataarize.exceptions;

public class MissingArgumentException extends RuntimeException{
    public MissingArgumentException(String message) {
        super(message);
    }
}
