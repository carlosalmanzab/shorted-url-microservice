package com.redirecturl.app.exception;

 public class UrlWasCleanedException extends RuntimeException {

    public UrlWasCleanedException() {
        super("URL doesn't exist or expired");
    }

    public UrlWasCleanedException(String message) {
        super(message);
    }
}
