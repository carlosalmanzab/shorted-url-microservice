package com.cleaner.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleaner.app.entity.Url;
import com.cleaner.app.services.CleanerService;

@RestController
@RequestMapping("/")
public class CleanerController {

    private final CleanerService cleanerService;

    public CleanerController(@Autowired CleanerService cleanerService) {
        this.cleanerService = cleanerService;
    }

    @GetMapping()
    public ResponseEntity<List<Url>> clean() {
        return ResponseEntity.ok(this.cleanerService.exec());
    }

}
