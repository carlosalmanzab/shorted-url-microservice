package com.redirecturl.app.exception_handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.redirecturl.app.exception.UrlWasCleanedException;

@ControllerAdvice
public class RestRedirectExceptionHandler extends ResponseEntityExceptionHandler {
    
    private final Logger log = LoggerFactory.getLogger(RestRedirectExceptionHandler.class);

    @ExceptionHandler(UrlWasCleanedException.class)
    public ProblemDetail handleUrlWasCleanedException(UrlWasCleanedException e) {
        log.error( "message, exception={}",e.getMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
