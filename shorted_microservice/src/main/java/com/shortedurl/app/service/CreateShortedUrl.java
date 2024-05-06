package com.shortedurl.app.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.shortedurl.app.dto.UrlDto;
import com.shortedurl.app.entity.Url;
import com.shortedurl.app.exception.BadWordException;
import com.shortedurl.app.repository.ForbiddenWordRepository;
import com.shortedurl.app.repository.UrlRepository;

@Service
public class CreateShortedUrl {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(CreateShortedUrl.class);

    private final Random random = new Random();

    private final UrlRepository urlRepository;

    private final ForbiddenWordRepository forbiddenWordRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    @Value("${redirect.service.host}")
    private String redirectServicehost;

    @Value("${redirect.service.port}")
    private int redirectServiceport;

    public CreateShortedUrl(@Autowired UrlRepository urlRepository,
            @Autowired ForbiddenWordRepository forbiddenWordRepository,
            @Autowired KafkaTemplate<String, String> kafkaTemplate) {
        this.urlRepository = urlRepository;
        this.forbiddenWordRepository = forbiddenWordRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public URI exec(UrlDto dto, UriComponentsBuilder builder) {
        long id;

        if (containtsBadWord(dto.url())) {
            log.warn("Bad word detected in url: {}", dto.url());
            log.info("Sending message to kafka: {}", dto.url());
            sendToKafka(dto.url());
            throw new BadWordException();
        }

        Url urlFound = urlRepository.findByLongUrl(dto.url());
        if (urlFound != null) {
            id = urlFound.getId();
        } else {
            do {
                id = random.nextLong(Long.MAX_VALUE);
            } while (urlRepository.existsById(id));

            Url url = new Url(id, dto.url(), LocalDateTime.now());
            urlRepository.save(url);
        }

        return builder
                .host(redirectServicehost)
                .port(redirectServiceport)
                .path("/{shortedUrl}")
                .buildAndExpand(id)
                .toUri();
    }

    private boolean containtsBadWord(String url) {
        return forbiddenWordRepository.findAll().stream()
                .anyMatch(word -> url.toLowerCase().contains(word.getWord()));
    }

    private void sendToKafka(String url) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, url);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Error sending message to kafka: {}", ex.getMessage());
                log.error("Unable to Sent message[{}] with offset[{}]", url, result.getRecordMetadata().offset());
            } else {
                log.info("Sent message[{}] with offset[{}]", url, result.getRecordMetadata().offset());
            }
        });
    }

}
