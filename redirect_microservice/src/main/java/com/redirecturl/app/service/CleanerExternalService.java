package com.redirecturl.app.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.redirecturl.app.entity.Url;

@Service
public class CleanerExternalService {

    @Value("${api.external.cleaner.url}")
    private String cleanerUrl;

    private final RestTemplate restTemplate;

    private final Logger log = org.slf4j.LoggerFactory.getLogger(CleanerExternalService.class);

    public CleanerExternalService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Url> exec() {
        ResponseEntity<Url[]> response = restTemplate
                .getForEntity(this.cleanerUrl, Url[].class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null
                || !response.hasBody()) {

            log.error("response.getBody()={}", (Object[]) response.getBody());
            return Collections.emptyList();
        }
        log.info("response.getBody()={}", (Object[]) response.getBody());

        return List.of(response.getBody());
    }
}
