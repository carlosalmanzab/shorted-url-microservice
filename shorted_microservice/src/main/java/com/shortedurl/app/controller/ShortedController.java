package com.shortedurl.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shortedurl.app.dto.UrlDto;
import com.shortedurl.app.service.CreateShortedUrl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/")
public class ShortedController {

    private final CreateShortedUrl createShortedUrl;

    public ShortedController(@Autowired CreateShortedUrl createShortedUrl) {
        this.createShortedUrl = createShortedUrl;
    }
    
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid UrlDto url, UriComponentsBuilder builder) {
        return ResponseEntity.created(createShortedUrl.exec(url, builder)).build();
    }
}
