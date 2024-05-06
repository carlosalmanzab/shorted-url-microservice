package com.redirecturl.app.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.redirecturl.app.service.RedirectService;

import jakarta.validation.constraints.Pattern;

@RestController
public class RedirectController {

    private final RedirectService redirectService;

    private final Logger log = LoggerFactory.getLogger(RedirectController.class);

    public RedirectController(@Autowired RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getLongUrl(@PathVariable("id") @Pattern(regexp = "^\\d+$") String shortedUrl) {
        log.info("path variable: {}", shortedUrl);
        String longUrl = redirectService.exec(shortedUrl);
        log.info("longUrl to redirect: {}", longUrl);
        URI uri = URI.create(longUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }

}
