package com.shortedurl.app.exception;

public class BadWordException extends RuntimeException {

    public BadWordException() {
        super("Url contains forbidden word");
    }

    public BadWordException(String message) {
        super(message);
    }
}
