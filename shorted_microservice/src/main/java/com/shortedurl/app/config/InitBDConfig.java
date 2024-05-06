package com.shortedurl.app.config;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shortedurl.app.entity.ForbiddenWord;
import com.shortedurl.app.entity.Url;
import com.shortedurl.app.repository.ForbiddenWordRepository;
import com.shortedurl.app.repository.UrlRepository;

@Component
public class InitBDConfig implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(InitBDConfig.class);
    private final ForbiddenWordRepository forbiddenWordRepository;
    private final UrlRepository urlRepository;

    private final Random random = new Random();

    public InitBDConfig(@Autowired ForbiddenWordRepository forbiddenWordRepository,
            @Autowired UrlRepository urlRepository) {
        this.forbiddenWordRepository = forbiddenWordRepository;
        this.urlRepository = urlRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadInitialData();
        loadExpiredData();
    }

    public void loadInitialData() {
        List<ForbiddenWord> forbiddenWords = forbiddenWordRepository.findAll();

        if (forbiddenWords.isEmpty()) {
            log.info(" Loading initial data...");

            forbiddenWordRepository.saveAll(List.of(
                    new ForbiddenWord(UUID.randomUUID(), "gambling", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "porn", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "hate", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "violence", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "racism", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "sexism", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "nigga", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "nigger", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "nazi", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "nig", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "drug", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "weed", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "cocaine", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "marijuana", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "alcohol", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "money", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "hacking", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "scam", LocalDateTime.now()),
                    new ForbiddenWord(UUID.randomUUID(), "fraud", LocalDateTime.now())));
            log.info("Initial data loaded");

        }

    }

    public void loadExpiredData() {
        // expiret dates

        long id;
        do {
            id = random.nextLong(Long.MAX_VALUE);
        } while (urlRepository.existsById(id));

        Url url = new Url(id, "https://www.google.com", LocalDateTime.of(2024, 4, 1, 0, 0, 0));
        urlRepository.save(url);
    }
}
